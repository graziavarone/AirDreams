package gestionevolo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FiltraRisultatiServlet
 */
@WebServlet("/FiltraRisultatiServlet")
public class FiltraRisultatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraRisultatiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Volo> voliAndataDiretti=(ArrayList<Volo>)request.getSession().getAttribute("voliAndataDiretti");
		  ArrayList<Volo> voliRitornoDiretti=(ArrayList<Volo>)request.getSession().getAttribute("voliRitornoDiretti");
		
		  ArrayList<Volo[]> voliAndataUnoScalo=(ArrayList<Volo[]>)request.getSession().getAttribute("voliAndataUno");
		  ArrayList<Volo[]> voliRitornoUnoScalo=(ArrayList<Volo[]>)request.getSession().getAttribute("voliRitornoUno");
		  
		  ArrayList<Volo[]> voliAndataDueScali=(ArrayList<Volo[]>)request.getSession().getAttribute("voliAndataDue");
		  ArrayList<Volo[]> voliRitornoDueScali=(ArrayList<Volo[]>)request.getSession().getAttribute("voliRitornoDue");

		  
		String direttoAndata=request.getParameter("DirettoAndata");
		String direttoRitorno=request.getParameter("DirettoRitorno");
		String durataAndata=request.getParameter("durataAndata");
		String durataRitorno=request.getParameter("durataRitorno");
		String prezzoAndata=request.getParameter("prezzoAndata");
		String prezzoRitorno=request.getParameter("prezzoRitorno");
		
		int durataA=Integer.parseInt(durataAndata);
		int durataR=Integer.parseInt(durataRitorno);
		int prezzoA=Integer.parseInt(prezzoAndata);
		int prezzoR=Integer.parseInt(prezzoRitorno);
		
		System.out.println(durataA);
		System.out.println(durataR);
		System.out.println(prezzoA);
		System.out.println(prezzoR);
		
		VoloManager voloManager=new VoloManager();
		
		if(direttoAndata!=null) {
			voliAndataDiretti=voloManager.cercaDiretti(voliAndataDiretti.get(0).getAeroportoP().getCodice(), 
					voliAndataDiretti.get(0).getAeroportoA().getCodice(), voliAndataDiretti.get(0).getDataPartenza(), 0, durataA,prezzoA);
			
			request.getSession().setAttribute("voliAndataDiretti", voliAndataDiretti);
			request.getSession().setAttribute("voliRitornoDiretti", voliRitornoDiretti);
			voliAndataUnoScalo.clear();
			request.getSession().setAttribute("voliAndataUno",voliAndataUnoScalo);
			request.getSession().setAttribute("voliRitornoUno",voliRitornoUnoScalo);
			voliAndataDueScali.clear();
			request.getSession().setAttribute("voliAndataDue",voliAndataDueScali);
			request.getSession().setAttribute("voliRitornoDue",voliRitornoDueScali);
		} else if(direttoRitorno!=null) {
			voliRitornoDiretti=voloManager.cercaDiretti(voliRitornoDiretti.get(0).getAeroportoP().getCodice(), 
				voliRitornoDiretti.get(0).getAeroportoA().getCodice(), voliRitornoDiretti.get(0).getDataPartenza(), 0, durataR,prezzoR);
			
			
			request.getSession().setAttribute("voliAndataDiretti", voliAndataDiretti);
			request.getSession().setAttribute("voliRitornoDiretti", voliRitornoDiretti);
			request.getSession().setAttribute("voliAndataUno",voliAndataUnoScalo);
			voliRitornoUnoScalo.clear();
			request.getSession().setAttribute("voliRitornoUno",voliRitornoUnoScalo);
			request.getSession().setAttribute("voliAndataDue",voliAndataDueScali);
			voliRitornoDueScali.clear();
			request.getSession().setAttribute("voliRitornoDue",voliRitornoDueScali);
		} else {
			if(voliAndataDiretti.size()!=0 && voliAndataDiretti!=null) {
				voliAndataDiretti=voloManager.cercaDiretti(voliAndataDiretti.get(0).getAeroportoP().getCodice(), 
						voliAndataDiretti.get(0).getAeroportoA().getCodice(), voliAndataDiretti.get(0).getDataPartenza(), 0,durataA,prezzoA);
				}
			
			if(voliAndataUnoScalo.size()!=0 && voliAndataUnoScalo!=null) {
				voliAndataUnoScalo=voloManager.cercaUnoScalo(voliAndataUnoScalo.get(0)[0].getAeroportoP().getCodice(),
						voliAndataUnoScalo.get(0)[1].getAeroportoA().getCodice(), voliAndataUnoScalo.get(0)[0].getDataPartenza() , 0,durataA,prezzoA);
				}
				
				if(voliAndataDueScali.size()!=0 && voliAndataDueScali!=null) {
					voliAndataDueScali=voloManager.cercaDueScali(voliAndataDueScali.get(0)[0].getAeroportoP().getCodice(),
							voliAndataDueScali.get(0)[2].getAeroportoA().getCodice(), voliAndataDueScali.get(0)[0].getDataPartenza() , 0,durataA,prezzoA);
					}
				
				if(voliRitornoDiretti!=null && voliRitornoDiretti.size()!=0) {
				voliRitornoDiretti=voloManager.cercaDiretti(voliRitornoDiretti.get(0).getAeroportoP().getCodice(), 
						voliRitornoDiretti.get(0).getAeroportoA().getCodice(), voliRitornoDiretti.get(0).getDataPartenza(), 0, durataR,prezzoR);
				
				}
				
				if(voliRitornoUnoScalo!=null && voliRitornoUnoScalo.size()!=0) {
					voliRitornoUnoScalo=voloManager.cercaUnoScalo(voliRitornoUnoScalo.get(0)[0].getAeroportoP().getCodice(),
							voliRitornoUnoScalo.get(0)[1].getAeroportoA().getCodice(), voliRitornoUnoScalo.get(0)[0].getDataPartenza() , 0,durataR,prezzoR);
				}
				
				if(voliRitornoDueScali!=null && voliRitornoDueScali.size()!=0) {
					voliRitornoDueScali=voloManager.cercaDueScali(voliRitornoDueScali.get(0)[0].getAeroportoP().getCodice(),
							voliRitornoDueScali.get(0)[1].getAeroportoA().getCodice(), voliRitornoDueScali.get(0)[0].getDataPartenza() , 0,durataR,prezzoR);
				}
				
				
				request.getSession().setAttribute("voliAndataDiretti", voliAndataDiretti);
				request.getSession().setAttribute("voliRitornoDiretti", voliRitornoDiretti);
				request.getSession().setAttribute("voliAndataUno",voliAndataUnoScalo);
				request.getSession().setAttribute("voliRitornoUno",voliRitornoUnoScalo);
				request.getSession().setAttribute("voliAndataDue",voliAndataDueScali);
				request.getSession().setAttribute("voliRitornoDue",voliRitornoDueScali);
		} 
		
	


		 RequestDispatcher dispatcher = request.getRequestDispatcher("risultatiRicerca.jsp");
	     dispatcher.forward(request, response);
		
	
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
