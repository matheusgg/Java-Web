package controler;

public class Teste {
	private int text;

	public int getText() {
		return text;
	}

	public void setText(int text) {
		this.text = text;
	}
	
	public String verificaTextoDigitado(){
		System.out.println(text);
		if (text == 10) {
			return "erro";
		}else{
			return "sucesso";
		}
		
	}
}
