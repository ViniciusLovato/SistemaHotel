package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import static java.util.concurrent.TimeUnit.*;



public class LoginServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response){

		try{

			// Login out
			if(request.getParameter("exit") != null && request.getParameter("exit").equals("true")){
				// request.getSession().invalidate();
				
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado",false);
				session.removeAttribute("usuario");


				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}

			// Login in
			else {

				/* se nao existe lista de usuarios na sessao, entao criar uma */				
				HttpSession session = request.getSession();
				if(session.getAttribute("usuarios")==null){

					session.setAttribute("usuarios",Dados.iniciaUsuarios());

				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("/login/index.html");
				dispatcher.forward(request, response);
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		try{
			String url, log ="LOG:\n\n";
			boolean erro = false, autenticacaoOk = false, usuarioBloqueado = false;

			ArrayList usuarios;

			System.out.println("\n\n\nALOU\n\n\n");

			/* se nao existe lista de usuarios na sessao, entao criar uma */				
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarios")==null){ 

				usuarios = Dados.iniciaUsuarios();
				session.setAttribute("usuarios",usuarios);

			}else{
				/* recuperar a lista de usuarios da sessao */
				usuarios = (ArrayList) session.getAttribute("usuarios");
			}

			/* Verifica se os dados foram recebidos corretamente */
			if ((request.getParameter("email") == null) || (request.getParameter("senha") == null)){
				erro = true;
				url = "erro.jsp";
				session.setAttribute("erro","400: Requisição Inválida!");

			}else{

				/* Obtem email e senha */
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");

				Usuario usuario = null, usuario_aux;

				log += "LOGIN: \n";
				log += "email: "+email+"\n";
				log += "senha: "+senha+"\n";

				log += "usuarios size: "+usuarios.size()+"\n";


				/* Busca o usuário com o dado email */
				for (int i = 0; i < usuarios.size(); i++) {

					log += "usuario " + i + "\n"; 
					usuario_aux = (Usuario) usuarios.get(i);

					/* Caso o usuário foi encontrado, verificamos:
				
						1: Se a senha está correta
							-> caso contrario, adiciona "new Date()"" a uma tentativa de acesso

						2: Com a senha correta, verifica se usuário está bloqueado
					*/
				    if (usuario_aux.getEmail().equalsIgnoreCase(email)) {
				    	usuario = usuario_aux;

						ArrayList<Date> tentativas = usuario.getTentativasAcesso();

						log += "a \n"; 


						/* Verifica por senha correta */
						if (!usuario.getSenha().equals(senha)){
							log += "b \n"; 

							/* Insere tentativa de acesso */
							tentativas.add(new Date());
							usuario.setTentativasAcesso(tentativas);

						/* Senha correta */
						}else{
							log += "c \n"; 

							/* Verifica se usuário está bloqueado */
							if(tentativas.size() >= 3){
								log += "d \n"; 

								/* Obtem as 3 tentivas mais recentes, (apenas a primeira e ultima) */
								Collections.sort(tentativas);
								Date t1 = tentativas.get(tentativas.size()-1);
								Date t3 = tentativas.get(tentativas.size()-3);

								/* Variaveis contento o tempos de comparação para utilização nas compareções */
								long BLOCK_15MIN = MILLISECONDS.convert(15, MINUTES);
								long BLOCK_1HORA = MILLISECONDS.convert(60, MINUTES);

								/* Diferença entre as duas tentativas (desconsiderando a do meio)*/
								long duracao = t1.getTime() - t3.getTime();

								/* Se elas possuem diferença menor que 15min */
								if (duracao <= BLOCK_15MIN){
									log += "e \n"; 

									/* Tempo desde a última tentativa falha de login */
									Date agora = new Date();
									long duracao_agora = agora.getTime() - t1.getTime();

									/* Verifica se já passou uma hora desde a última tentativa */
									if (duracao_agora <= BLOCK_1HORA){
										log += "f \n"; 
								
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
						session.setAttribute("erro","Usuário Bloqueado!");
					}else{
						session.setAttribute("erro","Email e/ou senha inválidos!");
					}
					session.setAttribute("debug",log);

					url = "erro.jsp";

				}else{
					/* Autentica o usuário */
					session.setAttribute("usuarioLogado",true);
					session.setAttribute("usuario",usuario);

					/* Se existe parametro 'next', redireciona o usuario para la*/
					if(request.getParameter("email").equals("admin@admin.com")){
						session.setAttribute("adminLogado", true);
						url = "admin/index.jsp";
					/* Senão, vai para o index */
					}else{
						url = "index.jsp";
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
