

public class ECB {
	private int length;
	private int[] key;
	
	public ECB(int n) {
		length = n;
		key = new int[3];
		for(int i = 0; i < 3; i ++)
			key[i] = (int) (Math.random() * 2);
		
	}
	
	public String encrypt(int b) {
		
		//Checking if they are following the length set
		String check = b + "";
		if(check.length() != length)
			throw new IndexOutOfBoundsException("You need to make your message the length you originally set to: " + length);
		check = Integer.toBinaryString(b);
		
		
		//padding since I don't know if i can add zeroes and ones
		if(check.length()%3 == 1) 
			check = "00" + check;
		else if(check.length()%3 == 2)
			check = "0" + check;
		//actually encrypting it
		for(int i = 0; i < check.length(); i++) {
			int cur = Integer.parseInt(check.substring(i, i+1));
			cur += key[i%3];
			cur %= 2;
			check = check.substring(0,i) + cur + check.substring(i + 1);
		}
		
		return check;
	}
	
	public String[] encryptB(int[] b){
		String check = "";
		String[] ret = new String[b.length];
		
		// setting up for cheking and then encryption
		for(int i  = 0; i < b.length; i++) {
			check += b[i] + "";
			ret[i] = Integer.toBinaryString(b[i]);
		}
		
		if(check.length() != length)
			throw new IndexOutOfBoundsException("You need to make your message the length you originally set to: " + length);
		
		//encryption
		int f = 0;
		for(int x = 0; x < ret.length; x++) {
			for(int i = 0; i < ret[x].length(); i++) {
				String a = ret[x];
				int cur = Integer.parseInt(a.substring(i, i+1));
				cur += key[f%3];
				cur %= 2;
				f++;
				a = a.substring(0,i) + cur + a.substring(i+1);
				ret[x] = a;
				
			}
		}
		return ret;
	}
}
