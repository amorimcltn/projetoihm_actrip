package br.edu.ifba.plugin.PROJETO.modelo.bd.estatico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HotelDAO {
	
	private static Map<Integer, Hotel> hoteis = 
			new TreeMap<Integer, Hotel>();
	
	static {
		
		Hotel h1 = new Hotel();
		h1.setId(1);
		h1.setNome("Iguatemi");
		h1.setTelefone("(77) 4444-5555");
		h1.setEndereco("Rua Tal de Aquilo 44, Centro");
		h1.setCidade("Vitória da Conquista");
		h1.setUf("BA");
		
		hoteis.put(h1.getId(), h1);
		
		Hotel h2 = new Hotel();
		h2.setId(2);
		h2.setNome("Costa Azul");
		h2.setTelefone("(77) 5555-4444");
		h2.setEndereco("Rua Aquilo de Tal 55, Centro");
		h2.setCidade("Vitória da Conquista");
		h2.setUf("BA");
		
		hoteis.put(h2.getId(), h2);		
		
		
	}
	
	public static List<Hotel> getHoteisPorNome(
			String nome)
	{
		List<Hotel> encontrados = new ArrayList<Hotel>();
		
		for (Hotel h : hoteis.values()) {
			if (h.getNome().toLowerCase().
					contains(nome.toLowerCase())) {
				encontrados.add(h);
			}
		}
		
		return encontrados;
	}
	
	public static Hotel getHotel(int id){
		return hoteis.get(id);
	}
	
	public static void remover(int id){
		hoteis.remove(id);		
	}
	
	public static int gravar(Hotel hotel){
		if(hotel.getId()!=-1){
			remover(hotel.getId());
			hoteis.put(hotel.getId(), hotel);
		}else{
			int ultimoId=0;
			for(int id : hoteis.keySet()){
				ultimoId = id;
			}
			hotel.setId(ultimoId+1);
			hoteis.put(ultimoId + 1, hotel);
		}
		return 0;
	}	

}
