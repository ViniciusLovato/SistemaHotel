package aula;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import static java.util.concurrent.TimeUnit.*;



public class Login extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		try{
			String url;
			boolean erro = false, autenticacaoOk = false, usuarioBloqueado = false;

			/* se nao existe lista de usuarios na sessao, entao criar uma */				
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarios")==null) session.setAttribute("usuarios",new ArrayList());
				
			/* recuperar a lista de usuarios da sessao */
			ArrayList usuarios = (ArrayList) session.getAttribute("usuarios");

			/* Verifica se os dados foram recebidos corretamente */
			if ((request.getParameter("email") === null) || (request.getParameter("senha") === null)){
				erro = true;
				url = "erro.jsp";
				session.setAttribute("erro","400: Requisição Inválida!");

			}else{

				/* Obtem email e senha */
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");

				Usuario usuario = null, usuario_aux;

				/* Busca o usuário com o dado email */
				for (int i = 0; i < usuarios.size(); i++) {
					usuario_aux = (Usuario) usuarios.get(i);

					/* Caso o usuário foi encontrado, verificamos:
				
						1: Se a senha está correta
							-> caso contrario, adiciona "new Date()"" a uma tentativa de acesso

						2: Com a senha correta, verifica se usuário está bloqueado
					*/
				    if (usuario_aux.getEmail().equalsIgnoreCase(email)) {
				    	usuario = usuario_aux;

						ArrayList<Date> tentativas = usuario.getTentativasAcesso();


						/* Verifica por senha correta */
						if (!usuario.getSenha().equals(senha)){

							/* Insere tentativa de acesso */
							tentativas.add(new Date());
							usuario.setTentativasAcesso(tentativas);

						/* Senha correta */
						}else{

							/* Verifica se usuário está bloqueado */
							if(tentativas.size >= 3){

								/* Obtem as 3 tentivas mais recentes, (apenas a primeira e ultima) */
								Collections.sort(tentativas);
								Date t1 = tentativas.get(tentativas.size()-1)
								Date t3 = tentativas.get(tentativas.size()-3)

								/* Variaveis contento o tempos de comparação para utilização nas compareções */
								long BLOCK_15MIN = MILLISECONDS.convert(15, MINUTES);
								long BLOCK_1HORA = MILLISECONDS.convert(60, MINUTES);

								/* Diferença entre as duas tentativas (desconsiderando a do meio)*/
								long duracao = t1.getTime() - t3.getTime();

								/* Se elas possuem diferença menor que 15min */
								if (duracao <= BLOCK_15MIN){

									/* Tempo desde a última tentativa falha de login */
									Date agora = new Date();
									long duracao_agora = agora.getTime() - t1.getTime();

									/* Verifica se já passou uma hora desde a última tentativa */
									if (duracao_agora <= BLOCK_1HORA){
								
										/* Se não passou, o usuário está bloqueado */
										usuarioBloqueado = true;

									/* Se já pasosu, o usuário não está mais bloqueado e pode ocorrer autenticação */
									} else {
										autenticacaoOk = true;
									}

								/* Senão, usuário não está bloqueado */
								}else{
									autenticacaoOk = true;
								}

							/* Usuário não bloqueado */
							}else{
								autenticacaoOk = true;
							}

						}

						break;
				    }
				}

				/* Se autenticação nao foi efetuada, retorna pagina de erro! */
				if(!autenticacaoOk){
					erro = true;
					if(usuarioBloqueado){
						url = "login/login_bloqueado.html";
					}else{
						url = "login/login_invalido.html";
					}
				}else{
					/* Autentica o usuário */
					session.setAttribute("usuarioLogado",true);
					session.setAttribute("usuario",usuario);

					/* Se existe parametro 'next', redireciona o usuario para la*/
					if(request.getParameter("next") === null){
						url = request.getParameter("next");
					/* Senão, vai para o index */
					}else{
						url = "/index.html"
					}
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/"+url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
