//Suhaib Al Akkari
//K00299139
//Project 2023
//Risk II inspired game

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Project2023
{
	public static void main(String[] args) 
	{
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);

		int urnation[] = {1000,30,50,1000};
		int enemynation[] = {0,30,50,1000};
		String[] nations = {"USSR","Germany","Japan","USA","UK"};
		String urname = null;
		String nationselection = null;
		int input = 0; //for menus
		int input2 =0; //for buy/sell menus
		String inputS; //for y/n
		char inp; //for inputS
		int min = 1;
		int urmax = 5;
		int enemymax = 5;
		int urdice = 0;
		int enemydice = 0;
		int urarty = 0;
		int enemyarty = 0;
		int checkair = 0; //for adaptive dice
		int checkarmour = 0; //for adaptive dice
		int wincounter = 0;
		int counter = nations.length-1; //to display the nations menu
		boolean temp = false; //y/n check

		System.out.println("1. New Game");
		System.out.println("2. Game Rules");
		System.out.println("3. Exit");

		input = scan.nextInt();

		while(input<1 || input>3)
		{
			System.out.println("Please Enter 1-3");
			input = scan.nextInt();
		}

		if(input==2)
		{
			System.out.println("Please use numbers to select from the menus unless instructed otherwise");
			System.out.println("Select your nation and conqure the world");
			System.out.println("Each nation starts with 30 aircraft, 50 armoured vehicles, and 1000 infantry");
			System.out.println("The dice has a chance to roll 1 higher for winning the air/armour battle, or for each extra 5 aircraft, 5 armoured vehicles, or 200 infantry");
			System.out.println("Warfunds are used to buy aircraft/armoured vehicles/infantry, make sure to restock after each battle");
			System.out.println("Artillery/Air defense has 20% chance of hitting its target");
			System.out.println("Captured the defeated nation's aircraft and armoured vehicles after winning the infantry battle");
			System.out.println("Be the last nation standing");
			System.out.println("Good luck commander :)");
			System.out.println("Start a New Game? Y/N");

			inputS = scan.next();
			inp = inputS.charAt(0);
			if(inp=='Y' || inp=='y' || inp=='N' || inp=='n')
			{
				temp = true;
			}

			while(!temp)
			{
				System.out.println("Please select Y or N");
				inputS = scan.next();
				inp = inputS.charAt(0);
				if(inp=='Y' || inp=='y' || inp=='N' || inp=='n')
				{
					temp = true;
				}
			}

			if(inp=='N' || inp=='n')
			{
				System.out.println("Thanks for playing");
				System.exit(0);
			}
		}
		else if(input==3)
		{
			System.out.println("Thanks for playing");
			System.exit(0);
		}

		System.out.println("Please Enter your name");
		urname = scan.nextLine();
		urname = scan.nextLine();
		//added 2 scan nextLine because the first one is being ignored and I couldnt figure out why

		System.out.println("Select your nation");
		for(int i=0;i<nations.length;i++)
		{
			System.out.println((i+1)+". "+nations[i]);
		}

		input = scan.nextInt();

		while(input<1 || input>nations.length)
		{
			System.out.println("Please Enter 1-"+nations.length);
			input = scan.nextInt();
		}

		nationselection = nations[input-1];
		System.out.println("Commander "+urname+", You are the leader of "+nationselection);
		nations[input-1] = "0";

		for(int i=0;i<nations.length-1;i++)
		{
			if(nations[i]=="0")
			{
				nations[i] = nations[i+1];
				nations[i+1] = "0";
			}
		}//rearranging nations[]

		//The Game loop

		while(urnation[3]!=0 && nations[0]!="0")
		{

			input = 0;

			//Buy/Sell/Attack menu

			while(input!=3)
			{	
				System.out.println("You have "+urnation[0]+" warfunds");
				System.out.println("You have "+urnation[1]+" aircraft");
				System.out.println("You have "+urnation[2]+" armoured vehicles");
				System.out.println("You have "+urnation[3]+" infantry");
				System.out.println("1. Buy");
				System.out.println("2. Sell");
				System.out.println("3. Attack");

				input = scan.nextInt();

				while(input<1 || input>3)
				{
					System.out.println("Please Enter 1-3");
					input = scan.nextInt();
				}

				if(input==1)
				{
					System.out.println("1. 5 Aircraft          (200 warfunds)");
					System.out.println("2. 5 Armoured vehicles (150 warfunds)");
					System.out.println("3. 100 Infantry        (100 warfunds)");
					System.out.println("4. Back");

					input2 = scan.nextInt();

					while(input2<1 || input2>4)
					{
						System.out.println("Please Enter 1-3");
						input2 = scan.nextInt();
					}

					switch(input2)
					{
						case 1:
							if(urnation[0]<200)
							{
								System.out.println("Insufficient warfunds");
								break;
							}
							else
							{
								urnation[input2] += 5;
								urnation[0] -= 200;
								break;
							}
						case 2:
							if(urnation[0]<150)
							{
								System.out.println("Insufficient warfunds");
								break;
							}
							urnation[input2] += 5;
							urnation[0] -= 150;
							break;
						case 3:
							if(urnation[0]<100)
							{
								System.out.println("Insufficient warfunds");
								break;
							}
							urnation[input2] += 100;
							urnation[0] -= 100;
							break;
						default:
							break;
					}
				}
				else if(input==2)
				{
					System.out.println("1. 6 Aircraft          (200 warfunds)");
					System.out.println("2. 6 Armoured vehicles (150 warfunds)");
					System.out.println("3. 120 Infantry        (100 warfunds)");
					System.out.println("4. Back");

					input2 = scan.nextInt();

					while(input2<1 || input2>4)
					{
						System.out.println("Please Enter 1-4");
						input2 = scan.nextInt();
					}

					switch(input2)
					{
						case 1:
							if(urnation[input2]<6)
							{
								System.out.println("You do not have enough aircraft");
								break;
							}
							else
							{
								urnation[input2] -= 6;
								urnation[0] += 200;
								break;
							}
						case 2:
							if(urnation[input2]<6)
							{
								System.out.println("You do not have enough armoured vehicles");
								break;
							}
							else
							{
								urnation[input2] -= 6;
								urnation[0] += 150;
								break;
							}
						case 3:
							if(urnation[input2]<100)
							{
								System.out.println("You do not have enough infantry");
								break;
							}
							else
							{
								urnation[input2] -= 120;
								urnation[0] += 100;
								break;
							}
						default:
							break;
					}
				}
			}

			//Attacking

			System.out.println("Select a nation to attack");
			for(int i=0;i<counter;i++)
			{
				System.out.println((i+1)+". "+nations[i]);
			}

			input = scan.nextInt();
			enemynation[0] = input;

			while(input<1 || input>counter)
			{
				System.out.println("Please Enter 1-"+counter);
				input = scan.nextInt();
				enemynation[0] = input;
			}

			//air battle
			
			System.out.println("______________________________");
			System.out.println("The air battle is starting");
			System.out.println("                     __|__");
			System.out.println("            __|__ *---o0o---*");
			System.out.println("   __|__ *---o0o---*");
			System.out.println("*---o0o---*");
			System.out.println("_____________________________");
			//art source www.asciiart.eu/vehicles/airplanes
			System.out.println("We have "+urnation[1]+" aircraft | The enemy has "+enemynation[1]+" aircraft");

			if(urnation[1]>=enemynation[1])
			{
				urmax += (urnation[1]-enemynation[1])/5;
			}
			else
			{
				enemymax += (enemynation[1]-urnation[1])/5;
			}

			scan.nextLine();
			//added this because the code ignores the first scan nextLine in the while

			while(urnation[1]>0 && enemynation[1]>0)
			{
				System.out.println("Press Enter to roll the dice");
				scan.nextLine();

				if(urnation[1]>urmax)
				{
					urdice = rand.nextInt(urmax-min+1)+min;
				}
				else
				{
					urdice = rand.nextInt(urnation[1]-min+1)+min;
				}
				System.out.println("Your dice is "+urdice);

				if(enemynation[1]>enemymax)
				{
					enemydice = rand.nextInt(enemymax-min+1)+min;
				}
				else
				{
					enemydice = rand.nextInt(enemynation[1]-min+1)+min;
				}
				System.out.println("The enemy's dice is "+enemydice);

				if(urnation[1]-enemydice>=0)
				{
					urnation[1] -= enemydice;
				}
				else
				{
					enemydice = urnation[1];
					urnation[1] = 0;
				}

				if(enemynation[1]-urdice>=0)
				{
					enemynation[1] -= urdice;
				}
				else
				{
					urdice = enemynation[1];
					enemynation[1] = 0;
				}

				System.out.println("The enemy lost "+urdice+" aircraft, they now have "+enemynation[1]+" aircraft");
				System.out.println("We lost "+enemydice+" aircraft, we now have "+urnation[1]+" aircraft");

				if(enemynation[1]>=2)
				{
					urarty = rand.nextInt(5);
					if(urarty == 0)
					{
						enemynation[1] -= 2;
						System.out.println("Our air defense shot down 2 enemy aircraft, they have "+enemynation[1]+" aircraft left");
					}
				}

				if(urnation[1]>=2)
				{
					enemyarty = rand.nextInt(5);
					if(enemyarty == 0)
					{
						urnation[1] -= 2;
						System.out.println("The enemy's air defense shot down 2 of our aircraft, we have "+urnation[1]+" aircraft left");
					}
				}
			}

			if(urnation[1]>0)
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has won the air battle");
				checkair = 1;
			}
			else if(enemynation[1]>0)
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has lost the air battle");
				checkair = 2;
			}
			else
			{
				System.out.println("__________________________");
				System.out.println("The air battle was a draw");
			}

			//the armour battle

			System.out.println("______________________________");
			System.out.println("The armour battle is starting");
			System.out.println("    _____           _____");
			System.out.println(" __|_>o<_|__	 __|_>o<_|__");
			System.out.println("|___________|	|___________|");
			System.out.println("|=|>-----<|=|	|=|>-----<|=|");
			System.out.println("_____________________________");
			//art source www.ascii-art.de/ascii/t/tank.txt (edited)
			System.out.println("We have "+urnation[2]+" armoured vehicles | The enemy has "+enemynation[2]+" armoured vehicles");
			
			urmax = 5;
			enemymax = 5;

			if(checkair==1)
			{
				urmax++;
			}
			else if(checkair==2)
			{
				enemymax++;
			}

			if(urnation[2]>=enemynation[2])
			{
				urmax += (urnation[2]-enemynation[2])/5;
			}
			else
			{
				enemymax += (enemynation[2]-urnation[2])/5;
			}

			while(urnation[2]>0 && enemynation[2]>0)
			{
				System.out.println("Press Enter to roll the dice");
				scan.nextLine();

				if(urnation[2]>urmax)
				{
					urdice = rand.nextInt(urmax-min+1)+min;
				}
				else
				{
					urdice = rand.nextInt(urnation[2]-min+1)+min;
				}
				System.out.println("Your dice is "+urdice);

				if(enemynation[2]>enemymax)
				{
					enemydice = rand.nextInt(enemymax-min+1)+min;
				}
				else
				{
					enemydice = rand.nextInt(enemynation[2]-min+1)+min;
				}
				System.out.println("The enemy's dice is "+enemydice);

				if(urnation[2]-enemydice>=0)
				{
					urnation[2] -= enemydice;
				}
				else
				{
					enemydice = urnation[2];
					urnation[2] = 0;
				}

				if(enemynation[2]-urdice>=0)
				{
					enemynation[2] -= urdice;
				}
				else
				{
					urdice = enemynation[2];
					enemynation[2] = 0;
				}

				System.out.println("The enemy lost "+urdice+" armoured vehicles, they now have "+enemynation[2]+" armoured vehicles");
				System.out.println("We lost "+enemydice+" armoured vehicles, we now have "+urnation[2]+" armoured vehicles");

				if(enemynation[2]>=2)
				{
					urarty = rand.nextInt(5);
					if(urarty == 0)
					{
						enemynation[2] -= 2;
						System.out.println("Our artillery destroyed 2 enemy armoured vehicles, they have "+enemynation[2]+" armoured vehicles left");
					}
				}

				if(urnation[2]>=2)
				{
					enemyarty = rand.nextInt(5);
					if(enemyarty == 0)
					{
						urnation[2] -= 2;
						System.out.println("The enemy's artillery destroyed 2 of our armoured vehicles, we have "+urnation[2]+" armoured vehicles left");
					}
				}
			}

			if(urnation[2]>0)
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has won the armour battle");
				checkarmour = 1;
			}
			else if(enemynation[2]>0)
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has lost the armour battle");
				checkarmour = 2;
			}
			else
			{
				System.out.println("The armuor battle was a draw");
			}

			//infantry battle

			System.out.println("_______________________________");
			System.out.println("The infantry battle is starting");
			System.out.println("   |                 |                 |                 |");
			System.out.println("   || .---.          || .---.          || .---.          || .---.");
			System.out.println("   ||/_____|         ||/_____|         ||/_____|         ||/_____|");
			System.out.println("   ||( '.' )         ||( '.' )         ||( '.' )         ||( '.' )");
			System.out.println("   ||  _-_/          ||  _-_/          ||  _-_/          ||  _-_/");
			System.out.println("   :- `'V'//-.       :- `'V'//-.       :- `'V'//-.       :- `'V'//-.");
			System.out.println("  / ,   |// , `,    / ,   |// , `,    / ,   |// , `,    / ,   |// , `,");
			System.out.println(" / /|Ll //Ll|| |   / /|Ll //Ll|| |   / /|Ll //Ll|| |   / /|Ll //Ll|| |");
			System.out.println("/ /||__//   || |  / /||__//   || |  / /||__//   || |  / /||__//   || |");
			System.out.println("| |/---|[]==|| |  | |/---|[]==|| |  | |/---|[]==|| |  | |/---|[]==|| |");
			System.out.println("| /|__/ |    | |  | /|__/ |    | |  |/|__/ |     | |   |/|__/ |    | |");
			System.out.println("|/ |_   | Ll_| |  |/|/_   | Ll_| |  |/|/_   | Ll_| |   /|/_   | Ll_| |");
			System.out.println(" `--|`^   ^`||_|   `--|`^   ^`||_|   `--|`^   ^`||_|   `--|`^   ^`||_|");
			System.out.println("    |   |   ||/       |   |   ||/       |   |   ||/       |   |   ||/");
			System.out.println("    |   |   |         |   |   |         |   |   |         |   |   |");
			System.out.println("    |   |   |         |   |   |         |   |   |         |   |   |");
			System.out.println("    |   |   |         |   |   |         |   |   |         |   |   |");
			System.out.println("    L___l___J         L___l___J         L___l___J         L___l___J");
			System.out.println("     |_ | _|           |_ | _|           |_ | _|           |_ | _|");
			System.out.println("    (___|___)         (___|___)         (___|___)         (___|___)");
			System.out.println("     ^^^ ^^^           ^^^ ^^^           ^^^ ^^^           ^^^ ^^^");
			System.out.println("____________________________________________________________________");
			//art source www.asciiart.eu/weapons/soldiers (edited)
			System.out.println("We have "+urnation[3]+" infantry | The enemy has "+enemynation[3]+" infantry");

			urmax = 5;
			enemymax = 5;

			if(checkarmour==1)
			{
				urmax++;
			}
			else if(checkarmour==2)
			{
				enemymax++;
			}

			if(checkair==1)
			{
				urmax++;
			}
			else if(checkair==2)
			{
				enemymax++;
			}

			if(urmax==6 && enemymax==6)
			{
				urmax--;
				enemymax--;
			}

			if(urnation[3]>=enemynation[3])
			{
				urmax += (urnation[3]-enemynation[3])/200;
			}
			else
			{
				enemymax += (enemynation[3]-urnation[3])/200;
			}

			while(urnation[3]>0 && enemynation[3]>0)
			{
				System.out.println("Press Enter to roll the dice");
				scan.nextLine();

				if(urnation[3]>urmax)
				{
					urdice = rand.nextInt(urmax-min+1)+min;
				}
				else
				{
					urdice = rand.nextInt(urnation[3]-min+1)+min;
				}
				System.out.println("Your dice is "+urdice);

				if(enemynation[3]>enemymax)
				{
					enemydice = rand.nextInt(enemymax-min+1)+min;
				}
				else
				{
					enemydice = rand.nextInt(enemynation[3]-min+1)+min;
				}
				System.out.println("The enemy's dice is "+enemydice);

				if(urnation[3]-enemydice*10>=0)
				{
					urnation[3] -= enemydice*10;
				}
				else
				{
					enemydice = urnation[3]/10;
					urnation[3] = 0;
				}

				if(enemynation[3]-urdice*10>=0)
				{
					enemynation[3] -= urdice*10;
				}
				else
				{
					urdice = enemynation[3]/10;
					enemynation[3] = 0;
				}

				System.out.println("The enemy lost "+(urdice*10)+" infantry, they now have "+enemynation[3]+" infantry");
				System.out.println("We lost "+(enemydice*10)+" infantry, we now have "+urnation[3]+" infantry");

				if(enemynation[3]>=20)
				{
					urarty = rand.nextInt(5);
					if(urarty == 0)
					{
						enemynation[3] -= 20;
						System.out.println("Our artillery killed 20 enemy infantry, they have "+enemynation[3]+" infantry left");
					}
				}

				if(urnation[3]>=20)
				{
					enemyarty = rand.nextInt(5);
					if(enemyarty == 0)
					{
						urnation[3] -= 20;
						System.out.println("The enemy's artillery killed 20 of our infantry, we have "+urnation[3]+" infantry left");
					}
				}
			}

			if(urnation[3]>0)
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has won the battle");
				System.out.println("__________________");
				urnation[0] += 1200;

				urnation[1] += enemynation[1];

				if (enemynation[1]!=0)
				{
					System.out.println("We have captured "+enemynation[1]+" aircraft");	
				}

				urnation[2] += enemynation[2];

				if (enemynation[2]!=0)
				{
					System.out.println("We have captured "+enemynation[2]+" armoured vehicles");		
				}
				wincounter++;
			}
			else
			{
				System.out.println("______________________________");
				System.out.println(nationselection+" has lost the battle");
			}

			urmax = 5;
			enemymax = 5;
			checkair = 0;
			checkarmour = 0;
			enemynation[1] = 30;
			enemynation[2] = 50;
			enemynation[3] = 1000;
			nations[enemynation[0]-1] = "0";

			for(int i=0;i<counter;i++)
			{
				if(nations[i]=="0")
				{
					nations[i] = nations[i+1];
					nations[i+1] = "0";
				}
			}//rearranging nations[]
			counter--;
		}

		System.out.println("__________________________________________________");

		if(wincounter==4)
		{
			System.out.println("Commander "+urname+", "+nationselection+" has won the war");
		}
		else
		{
			System.out.println("Commander "+urname+", "+nationselection+" was defeated");
		}

		System.out.println("__________________________________________________");
		System.out.println("Thanks for playing :D");
	}
}