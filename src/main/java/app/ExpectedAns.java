package app;

public enum ExpectedAns {
	YES(new String[] { "si", "s", "yes", "y", "1" }),
	NO(new String[] { "no", "n", "2" }),
	ACCEPT(new String[] { "aceptar", "a", "1", "si", "s" }),
	REJECT(new String[] { "rechazar", "r", "2", "no", "n" }),
	USERS(new String[] {});

	private String[] options;

	ExpectedAns(String[] options) {
		this.options = options;
	}

	public static String[] yes() {
		return YES.options;
	}

	public static String[] no() {
		return NO.options;
	}

	public static String[] accept() {
		return ACCEPT.options;
	}

	public static String[] reject() {
		return REJECT.options;
	}

	public static String[] users(int size) {
		String[] out = new String[size];
		for (int i = 0; i < size; i++)
			out[i] = Integer.toString(i+1);
		return out;
		}
	}
