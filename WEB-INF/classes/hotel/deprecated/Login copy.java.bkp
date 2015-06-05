package aula;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;


public class ServletLivroCadastro extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response){
		try{
			String url;

			/* se nao existe lista de clientes na sessao, entao criar uma */				
			HttpSession session = request.getSession();
			if(session.getAttribute("listaLivros")==null) session.setAttribute("listaLivros",new ArrayList());

			int id = Integer.valueOf(request.getParameter("id"));
				
			/* recuperar a lista de clientes da sessao */
			ArrayList listaLivros = (ArrayList) session.getAttribute("listaLivros");

			/* recuperando o cliente na posicao indicada */
			Livro livro = null, livro_aux;

			for (int i = 0; i < listaLivros.size(); i++) {
				livro_aux = (Livro) listaLivros.get(i);

			    if (livro_aux.getId() == id) {
			      livro = livro_aux;
			      break;
			    }

			}

			if (livro == null){
				session.setAttribute("erro","Erro 404: Livro não encontrado.");
				url = "erro.jsp";
			}else{
				url = "editar_livro.jsp";
				session.setAttribute("livro",livro);

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/"+url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		try{
			String url;

			/* se nao existe lista de clientes na sessao, entao criar uma */				
			HttpSession session = request.getSession();
			if(session.getAttribute("listaLivros")==null) session.setAttribute("listaLivros",new ArrayList());
				
			/* recuperar a lista de clientes da sessao */
			ArrayList listaLivros = (ArrayList) session.getAttribute("listaLivros");

			int id;
			boolean novo = false;
			Livro livro = null, livro_aux;

			/* Edição de livro */
			if ((request.getParameter("id") != null) && !request.getParameter("id").equals("")){
				id = Integer.valueOf(request.getParameter("id"));

				/* recuperando o cliente na posicao indicada */
				for (int i = 0; i < listaLivros.size(); i++) {
					livro_aux = (Livro) listaLivros.get(i);

				    if (livro_aux.getId() == id) {
				      livro = livro_aux;
				      break;
				    }
				}

			/* Inserção de um novo livro */
			}else{
				novo = true;
				int max = 0;

				for (int i = 0; i < listaLivros.size(); i++) {
					livro_aux = (Livro) listaLivros.get(i);
				    if (livro_aux.getId() > max) {
				      max = livro_aux.getId();
				    }
				}

				id = max+1;
				livro = new Livro();
				livro.setId(id);
			}



			if (livro == null){
				session.setAttribute("erro","Erro 404: Livro não encontrado.");
				url = "erro.jsp";
			}else{
				url = "lista_livros.jsp";
			}

			/* Insere os dados no livro criado/obtido */
			livro.setNome(request.getParameter("nome"));
			livro.setAutor(request.getParameter("autor"));
			livro.setDisponivel(( (request.getParameter("disponivel") != null) && !request.getParameter("disponivel").equals("")));
			
			if (novo){
				listaLivros.add(livro);
			}

			/* atualizando lista na sessao */
			session.setAttribute("listaLivros",listaLivros);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/"+url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
