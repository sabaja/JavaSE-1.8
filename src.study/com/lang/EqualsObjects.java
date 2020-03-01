package com.lang;

/* Name of the class has to be "Main" only if the class is public. */
class EqualsObjects {
	public static void main(String[] args) throws java.lang.Exception {
		Str p1 = new Str("", " ");
		Str p2 = new Str("", "");
		Str p3 = p2;
		System.out.println(p1.equals(p2));
		System.out.println(p3.equals(p2));

	}
}

class Str {
	private String a;
	private String b;

	public Str(String a, String b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int res = 1;
		res = res * prime + ((a == null) ? 0 : this.a.hashCode());
		res = res * prime + ((b == null) ? 0 : this.b.hashCode());
		return res;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null) 
			return false;
		if(this == obj)
			return true;
		if(this.getClass() != obj.getClass())
			return false;
		Str other = (Str) obj;
        if (this.a == null) {
            if (other != null)
                return false;
        } else if (!a.equals(other.a)) {
            return false;
        }
        if (this.b == null) {
            return this.b == null;
        } else return b.equals(other.a);
    }
}