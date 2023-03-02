package dao;

public class Test {
	
	static Pizza_IngredientsDAO pizza_ingrDAO = new Pizza_IngredientsDAO();
	
	public static void main(String[] args) {
		System.out.println("hello");
		try {
			System.out.println(pizza_ingrDAO.getIngredientsPizza(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("nonnnn");
		}
	}

}
