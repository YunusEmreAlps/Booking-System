import java.util.ArrayList; // API (linked list)
import java.sql.ResultSet; // API
import java.sql.Statement; // API
import java.util.Scanner; // API (input class)
import java.util.Locale; // API (for floating point number) (comma or dot)

public class Main { 

	// ----------
	// global
	static ArrayList<Book> books = new ArrayList<Book>(); // (linked list object)
	static Scanner scan = new Scanner(System.in); // (input object)
	static int counter = 0; // total book number
	static ConnectionClass cndb = new ConnectionClass(); 

	// ----------
	// main function
	public static void main(String[] args) {

		// ----------
		// set data in my ArrayList	
		if(counter ==0) {
			try {

				Statement dsb = cndb.dbconnect().createStatement();
				Book addbook2;
				ResultSet sdbd2 = dsb.executeQuery(" SELECT * FROM Booking"); // set database data

				while(sdbd2.next()) {

					char at=sdbd2.getString("autho_gn").charAt(0);
					addbook2 = new Book(sdbd2.getString("isb"),sdbd2.getString("boo_nm"),sdbd2.getString("autho_nm"),at,sdbd2.getString("publishe_nm"),sdbd2.getDouble("pri"));
					books.add(addbook2);
					counter++;	
				}
				dsb.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// ----------
		boolean control = false; // quit control

		while (true) { // infinity loop

			switch (menu()) {

			case 1: // add 
				adding();
				counter++;
				break;
			case 2: // update
				upd();
				break;
			case 3: // delete
				delt();
				break;
			case 4: // display
				disp();
				break;
			case 5: // search
				src();
				break;
			case 6: // quit
				control = true;
				break;
			}

			if (control == true) { // quit
				break;
			}
		} 

	}

	// ----------
	// main menu
	static int menu() {

		System.out.printf(" - ---------------\n");
		System.out.printf(" - 1.) Add book \n");
		System.out.printf(" - 2.) Update book information \n");
		System.out.printf(" - 3.) Delete book \n");
		System.out.printf(" - 4.) List all book \n");
		System.out.printf(" - 5.) Search \n");
		System.out.printf(" - 6.) Quit \n");
		System.out.printf(" - ---------------\n");
		System.out.printf(" - Enter choice : ");

		float mn_pro = 0; // menu process number

		while (true) {
			mn_pro = scan.nextFloat(); // input function

			if ((mn_pro == 1) || (mn_pro == 2) || (mn_pro == 3) || (mn_pro == 4) || (mn_pro == 5) || (mn_pro == 6)) {
				break;
			}
			System.out.printf(" - Oops!.Try again : "); // Error message
		}

		return (int)mn_pro; // type casting
	}

	// ----------
	// delete function menu (multiple deletion)
	static int deltmenu() {

		System.out.printf(" - ---------------\n");
		System.out.printf(" - 1.) Delete by ISBN number \n");
		System.out.printf(" - 2.) Delete by Title \n");
		System.out.printf(" - 3.) Quit \n");
		System.out.printf(" - ---------------\n");
		System.out.printf(" - Enter choice : ");

		float dlt_pro = 0; // delete process number

		while (true) {
			dlt_pro = scan.nextFloat(); // input function

			if ((dlt_pro == 1) || (dlt_pro == 2) || (dlt_pro == 3)) {
				break;
			}
			System.out.printf(" - Oops!.Try again : "); // Error message
		}

		return (int) dlt_pro; // type casting
	}

	// ----------
	// update function menu
	static int updmenu() {

		System.out.printf(" - ---------------\n");
		System.out.printf(" - 1.) Update by ISBN number \n");
		System.out.printf(" - 2.) Quit \n");
		System.out.printf(" - ---------------\n");
		System.out.printf(" - Enter choice : ");

		float upd_pro = 0; // update process number

		while (true) {
			upd_pro = scan.nextFloat(); // input function
			if ((upd_pro == 1) || (upd_pro == 2)) {
				break;
			}
			System.out.printf(" - Oops!.Try again : ");
		}

		return (int) upd_pro; // type casting
	}
	
	// ----------
	// search function menu
	static int srcmenu() {

		System.out.printf(" - ---------------\n");
		System.out.printf(" - 1.) Search by ISBN number \n");
		System.out.printf(" - 2.) Search by Title \n");
		System.out.printf(" - 3.) Quit \n");
		System.out.printf(" - ---------------\n");
		System.out.printf(" - Enter choice : ");

		float src_pro = 0; // update process number

		while (true) {
			src_pro = scan.nextFloat(); // input function
			if ((src_pro == 1) || (src_pro == 2) || (src_pro == 3)) {
				break;
			}
			System.out.printf(" - Oops!.Try again : ");
		}

		return (int) src_pro; // type casting
	}
	
	// ----------
	// ISBN control
	static boolean is_control(String is_number) {

		int tmp=1; // temp 
		int res=0; // result
		int a=0; 
		int f_ind=0; // first index 

		for(int i=0; i<is_number.length(); i++){
			tmp=1;
			if((int)(is_number.charAt(i)) == 48){a=0;}
			if((int)(is_number.charAt(i)) == 49){a=1;}
			if((int)(is_number.charAt(i)) == 50){a=2;}
			if((int)(is_number.charAt(i)) == 51){a=3;}
			if((int)(is_number.charAt(i)) == 52){a=4;}
			if((int)(is_number.charAt(i)) == 53){a=5;}
			if((int)(is_number.charAt(i)) == 54){a=6;}
			if((int)(is_number.charAt(i)) == 55){a=7;}
			if((int)(is_number.charAt(i)) == 56){a=8;}
			if((int)(is_number.charAt(i)) == 57){a=9;}

			if(i==0)
			{
				f_ind=a;
			}
			if(i!=0){

				if(i%2 ==0){
					tmp = a;
					res += tmp;
				}
				else{
					tmp = 3*a;
					res += tmp;
				}

			}
		}

		int last=0;
		last=res%10;
		last=10-last;

		if(last == f_ind)
		{
			return true;
		}
		else
		{
			System.out.println(" - Oops!.This is not ISBN-13 number.");
		}
		return false;
	}

	// ----------
	// add new book
	static void adding() {
		scan.useLocale(Locale.US); // use comma(17,99) or dot(17.99) 

		System.out.printf(" ---------------\n");
		// ----------
		// ISBN 
		String is_num; // ISBN 13 : 9780545010221
		System.out.printf(" - Enter ISBN-13 number : ");
		scan.nextLine(); // getchar();

		while (true) { // unique and 13 digit 
			is_num = scan.nextLine(); // input function

			boolean is_cont=false; // ISBN control variable
			if (is_num.length() == 13) { // digit control
				if(counter == 0) // list is empty
				{
					if(is_control(is_num)==true) {
						break;
					}

				}

				else if(counter != 0) // list is not empty
				{

					for(int i=0; i<counter ; i++){ // unique control
						if(books.get(i).getIsbn().compareTo(is_num)==0){
							is_cont=false;
							System.out.println(" - You Have this ISBN number in your list.");
							break;
						}
						else {
							is_cont=true;
						}
					}
				}

				if(is_cont==true){

					if(is_control(is_num)==true) {
						break;
					}
				}
			}
			System.out.printf(" - Oops!.Try again : ");
		}

		// ----------
		// Title
		String titl; // Harry Potter and the Deathly Hallows
		System.out.printf(" - Enter Title of Book : ");

		while(true){
			titl = scan.nextLine(); // input function

			if(titl.length() > 0)
				break;
			System.out.printf(" - Oops!.Try again.");
		}

		// ----------
		// Author
		String auth; // J.K.Rowling
		System.out.printf(" - Enter Name of Author : ");

		while(true) {
			auth = scan.nextLine(); // input function 

			if(auth.length() > 0)
				break;
			System.out.printf(" - Oops!.Try again.");
		}

		// ----------
		// Gender
		char gend; // Female or Male
		System.out.printf(" - Enter Gender of Author : ");

		while(true) {
			gend = scan.nextLine().toUpperCase().charAt(0); // just one character

			if((gend=='F')||(gend=='M'))
				break;
			System.out.printf(" - Oops!.Try again.");
		}

		// ----------
		// Publisher
		String publ; // Arthur A. Levine Books
		System.out.printf(" - Enter Name of Publisher : ");

		while(true) {
			publ = scan.nextLine(); // input function 

			if(publ.length() > 0)
				break;
			System.out.printf(" - Oops!.Try again.");
		}

		// Add a Database	

		// ----------
		// Price
		double pric; // 1.00$
		System.out.printf(" - Enter Price of Book : ");

		while(true) {
			pric = scan.nextDouble(); // input function 

			if(pric > 0)
				break;
			System.out.printf(" - Oops!.Try again.");
		}

		// set new data in my ArrayList

		try {

			Statement dsb2 = cndb.dbconnect().createStatement();
			dsb2.executeQuery("INSERT INTO Booking VALUES('"+is_num+"','"+titl+"','"+auth+"','"+gend+"','"+publ+"',"+pric+")");
			dsb2.close();

		} catch (Exception e) {
			System.out.println(e);
		}


		// Add ArrayList
		Book addbook = new Book(is_num, titl, auth, gend, publ, pric);
		books.add(addbook);
	}

	// ----------
	// update book information
	static void upd() {

		boolean updcontrol = false; // quit control

		while (true) {
			switch (updmenu()) {

			case 1: // ISBN number

				// ----------
				String upis_num; // delete ISBN number

				System.out.printf(" - Enter ISBN-13 number : ");
				scan.nextLine(); // getchar();

				while (true) { // unique and 13 digit 
					upis_num = scan.nextLine(); // input function

					boolean is_cont=false; // ISBN control variable
					if (upis_num.length() == 13) // digit control
						break;
					System.out.printf(" - Oops!.Try again : ");
				}

				// ----------
				for (int i = 0; i < counter; i++) {
					if (books.get(i).getIsbn().compareTo(upis_num) == 0) { // compare 

						// ----------
						// ISBN 
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change ISBN-13 number \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float nis_pro=0;

						while(true){
							nis_pro=scan.nextFloat(); // input function

							if((nis_pro==1)||(nis_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}

						if(nis_pro==1)
						{	
							String uis_num; // ISBN 13 : 9780545010221
							System.out.printf(" - Enter ISBN-13 number : ");
							scan.nextLine(); // getchar();

							while (true) { // unique and 13 digit 
								uis_num = scan.nextLine(); // input function

								boolean uis_cont=false; // ISBN control variable
								if (uis_num.length() == 13) { // digit control
									if(counter == 0) // list is empty
									{
										break; 
									}

									else if(counter != 0) // list is not empty
									{

										for(int j=0; j<counter ; j++){ // unique control
											if(books.get(j).getIsbn().compareTo(uis_num)==0){
												uis_cont=false;
												break;
											}
											else {
												uis_cont=true;
											}
										}
									}

									if(uis_cont==true){
										break;
									}
								}
								System.out.printf(" - Oops!.Try again : ");
							}
							
							try {	
								Statement dsbi = cndb.dbconnect().createStatement();
								dsbi.executeUpdate("UPDATE Booking SET isb ='"+uis_num+"' WHERE isb='"+(books.get(i).getIsbn())+"'");
								dsbi.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							books.get(i).setIsbn(uis_num);
							System.out.printf(" - Process is success.\n");
						}


						// ----------
						// Title
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change Title of Book \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float ntit_pro=0;

						while(true){
							ntit_pro=scan.nextFloat(); // input function

							if((ntit_pro==1)||(ntit_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}

						if(ntit_pro==1){
							String utitl; // Harry Potter and the Deathly Hallows
							System.out.printf(" - Enter Title of Book : ");
							scan.nextLine();
							while(true){
								utitl = scan.nextLine(); // input function

								if(utitl.length() > 0)
									break;
								System.out.printf(" - Oops!.Try again.");
							}

							books.get(i).setTitle(utitl);
							try {
								
								Statement dsbt = cndb.dbconnect().createStatement();
								dsbt.executeUpdate("UPDATE Booking SET boo_nm='"+utitl+"' WHERE isb='"+(books.get(i).getIsbn())+"'");
								dsbt.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.printf(" - Process is success.\n");
						}

						// ----------
						// Author
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change Name of Author \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float naut_pro=0;

						while(true){
							naut_pro=scan.nextFloat(); // input function

							if((naut_pro==1)||(naut_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}

						if(naut_pro==1){
							String uauth; // J.K.Rowling
							System.out.printf(" - Enter Name of Author : ");
							scan.nextLine();

							while(true) {
								uauth = scan.nextLine(); // input function 

								if(uauth.length() > 0)
									break;
								System.out.printf(" - Oops!.Try again.");	
							}

							books.get(i).setAuthor(uauth);
							try {	
								Statement dsba = cndb.dbconnect().createStatement();
								dsba.executeUpdate("UPDATE Booking SET autho_nm ='"+uauth+"' WHERE isb='"+books.get(i).getIsbn()+"'");
								dsba.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.printf(" - Process is success.\n");
						}

						// ----------
						// Gender
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change Gender of Author \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float ngen_pro=0;

						while(true){
							ngen_pro=scan.nextFloat(); // input function

							if((ngen_pro==1)||(ngen_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}


						if(ngen_pro==1){
							char ugend; // Female or Male
							System.out.printf(" - Enter Gender of Author : ");
							scan.nextLine();

							while(true) {
								ugend = scan.nextLine().toUpperCase().charAt(0); // just one character

								if((ugend=='F')||(ugend=='M'))
									break;
								System.out.printf(" - Oops!.Try again.");
							}
							books.get(i).setGender(ugend);
							try {	
								Statement dsbag = cndb.dbconnect().createStatement();
								dsbag.executeUpdate("UPDATE Booking SET autho_gn ='"+ugend+"' WHERE isb='"+books.get(i).getIsbn()+"'");
								dsbag.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.printf(" - Process is success.\n");
						}

						// ----------
						// Publisher
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change Name of Publisher \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float npub_pro=0;

						while(true){
							npub_pro=scan.nextFloat(); // input function

							if((npub_pro==1)||(npub_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}

						if(npub_pro==1){
							String upubl; // Arthur A. Levine Books
							System.out.printf(" - Enter Name of Publisher : ");
							scan.nextLine();

							while(true) {
								upubl = scan.nextLine(); // input function 

								if(upubl.length() > 0)
									break;
								System.out.printf(" - Oops!.Try again.");
							}
							books.get(i).setPublisher(upubl);
							try {	
								Statement dsbp = cndb.dbconnect().createStatement();
								dsbp.executeUpdate("UPDATE Booking SET publishe_nm ='"+upubl+"' WHERE isb='"+books.get(i).getIsbn()+"'");
								dsbp.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.printf(" - Process is success.\n");
						}

						// ----------
						// Price
						System.out.printf(" - ---------------\n");
						System.out.printf(" - 1.) Change Price of Book \n");
						System.out.printf(" - 2.) Not Change \n");
						System.out.printf(" - ---------------\n");
						System.out.printf(" - Enter choice : ");

						float npri_pro=0;

						while(true){
							npri_pro=scan.nextFloat(); // input function

							if((npri_pro==1)||(npri_pro==2))
								break;
							System.out.printf(" - Oops!.Try again.");	
						}

						if(npri_pro==1){
							scan.useLocale(Locale.US);
							double upric; // 1.00$
							System.out.printf(" - Enter Price of Book : ");

							while(true) {
								upric = scan.nextDouble(); // input function 

								if(upric > 0)
									break;
								System.out.printf(" - Oops!.Try again.");
							}
							books.get(i).setPrice(upric);
							try {	
								Statement dsbpri = cndb.dbconnect().createStatement();
								dsbpri.executeUpdate("UPDATE Booking SET pri ="+upric+" WHERE isb='"+books.get(i).getIsbn()+"'");
								dsbpri.close();

							} catch (Exception e) {
								System.out.println(e);
							}
							System.out.printf(" - Process is success.\n");
						}

					} //

				}

				break;

			case 2: // Quit
				updcontrol=true;
				break;
			}

			if(updcontrol==true)
				break;
		}
	}

	// ----------
	// delete book information
	static void delt() {

		boolean delcontrol = false; // quit control

		while (true) { // multiple deletion

			switch (deltmenu()) {

			case 1: // ISBN number

				// ----------
				String dis_num; // delete ISBN number

				System.out.printf(" - Enter ISBN-13 number : ");
				scan.nextLine(); // getchar();

				while (true) { // unique and 13 digit 
					dis_num = scan.nextLine(); // input function

					boolean is_cont=false; // ISBN control variable
					if (dis_num.length() == 13) // digit control
						break;
					System.out.printf(" - Oops!.Try again : ");
				}

				// ----------
				for (int i = 0; i < counter; i++) {
					if (books.get(i).getIsbn().compareTo(dis_num) == 0) { // compare 
						books.remove(books.get(i)); // delete book
						if(counter !=0) {
							try {

								Statement dsb3 = cndb.dbconnect().createStatement();
								dsb3.executeUpdate("DELETE FROM Booking WHERE isb ='"+dis_num+"'");
								dsb3.close();

							} catch (Exception e) {
								System.out.println(e);
							}
						}
						counter--; // total book number --
						System.out.printf(" - Process is success.\n");
						break;
					}
				}

				break;

			case 2: // Title of Book

				// ----------
				String dtitl; // delete Title of Book
				System.out.printf(" - Enter Title of Book : ");
				scan.nextLine();

				while(true){
					dtitl = scan.nextLine(); // input function

					if(dtitl.length() > 0)
						break;
					System.out.printf(" - Oops!.Try again.");
				}

				// ----------
				for (int i = 0; i<counter; i++) {
					if (books.get(i).getTitle().compareTo(dtitl) == 0) {
						books.remove(books.get(i));
						if(counter !=0) {
							try {

								Statement dsb3 = cndb.dbconnect().createStatement();
								dsb3.executeUpdate("DELETE FROM Booking WHERE boo_nm ='"+dtitl+"'");
								dsb3.close();

							} catch (Exception e) {
								System.out.println(e);
							}
						}
						counter--; // total book number --
						System.out.printf(" - Process is success.\n");
						break;
					}
				}

				break;

			case 3: // Quit
				delcontrol = true;
				break;
			}

			if (delcontrol == true) {
				break;
			}
		}

	}
	
	// ----------
	// search 
	static void src() {
		
		boolean srccontrol = false; // quit control
		
		// ----------
		while(true){
			
			switch(srcmenu())
			{
			case 1: // ISBN number
				
				// ----------
				String sr_num; // delete ISBN number
				System.out.printf(" - Enter ISBN-13 number : ");
				sr_num=scan.next(); // getchar();
				
				boolean srcon = false;
				
				// ----------
				for(int i=0; i<books.size(); i++)
				{
					if(books.get(i).getIsbn().compareTo(sr_num) == 0)
					{
						disp(i);
						srcon = true;
					}
				}
				if(srcon == false){ 
					System.out.println(" - This ISBN number have not your book list.");
				}
		
				break;
				
			case 2: // // Title of Book
				
				scan.nextLine();
				// ----------
				String srtitl; // delete Title of Book
				System.out.printf(" - Enter Title of Book : ");
				srtitl=scan.nextLine();
				
				boolean srcon2 = false;
				
				// ----------
				for(int i=0; i<books.size(); i++)
				{
					if(books.get(i).getTitle().compareToIgnoreCase(srtitl) == 0)
					{
						disp(i);
						srcon2 = true;
					}
				}
				if(srcon2 == false)
				{
					System.out.println(" - This ISBN number have not your book list.");
				}
				
				break;
				
			case 3: // quit
				srccontrol=true;
				break;
			}
			if(srccontrol==true)
			{
				break;
			}	
		}
	}

	// ----------
	// display all data
	static void disp() {
		
		for (int i = 0; i<counter; i++) {

			System.out.println();
			System.out.println(" - " + (i + 1) + " ISBN number : " + books.get(i).getIsbn());
			System.out.println(" - " + (i + 1) + " Title : " + books.get(i).getTitle());
			System.out.println(" - " + (i + 1) + " Author : " + books.get(i).getAuthor());
			System.out.println(" - " + (i + 1) + " Author gender : " + books.get(i).getGender());
			System.out.println(" - " + (i + 1) + " Publisher : " + books.get(i).getPublisher());
			System.out.println(" - " + (i + 1) + " Price : " + books.get(i).getPrice());
		}
	}
	
	// ----------
	// display data (overloading)
	static void disp(int ind) {
			
		System.out.println();
		System.out.println(" - ISBN number : " + books.get(ind).getIsbn());
		System.out.println(" - Title : " + books.get(ind).getTitle());
		System.out.println(" - Author : " + books.get(ind).getAuthor());
		System.out.println(" - Author gender : " + books.get(ind).getGender());
		System.out.println(" - Publisher : " + books.get(ind).getPublisher());
		System.out.println(" - Price : " + books.get(ind).getPrice());
	}

} // }
