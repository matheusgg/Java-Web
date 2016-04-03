package methodhandle;

public class MethodHandlerTest {

	public static void main(String[] args) throws Throwable {
		// Lookup lookup = MethodHandles.lookup();

		// MethodType methodType = MethodType.methodType(int.class, int.class,
		// int.class);
		// MethodHandle sumMethodHandle = lookup.findStatic(Bean.class, "sum",
		// methodType);
		// int sum = (int) sumMethodHandle.invokeExact(10, 20);
		// System.out.println(sum);

		// MethodHandle methodHandle = lookup.findGetter(Bean.class, "text",
		// String.class);
		// String text = (String) methodHandle.invokeExact(new Bean());
		// System.out.println(text);

		// Bean bean = new Bean();
		// System.out.println(bean);
		// MethodHandle methodHandle = lookup.findSetter(Bean.class, "text",
		// String.class);
		// methodHandle.invokeExact(bean, "Teste 2");
		// System.out.println(bean);

		// MethodType methodType = MethodType.methodType(void.class, new
		// Class<?>[] {});
		// MethodHandle methodHandle = lookup.findVirtual(Bean.class, "show",
		// methodType);
		// methodHandle.invokeExact(new Bean());
	}
}

class Bean {

	String text;

	public Bean() {
		this.text = "Teste";
	}

	public static int sum(int x, int y) {
		return x + y;
	}

	public void show() {
		System.out.println("Bean.show()");
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bean [text=" + text + "]";
	}

}