
// given initial number less than 10
// asks for additional number(s) greater than 0
// and prints out if not equal to 1

String str = System.console().readLine();
int i = Integer.parseInt(str);
while (i < 10) {
	i++;
	String str2 = System.console().readLine();
	int j = Integer.parseInt(str2);
	if (j == 0) {
		break;
	} else if (j != 1) {
		println j;
	}
}

System.out.println("finished");