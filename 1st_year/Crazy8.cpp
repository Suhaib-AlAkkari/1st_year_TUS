//Suhaib Al Akkari
//
//Crazy8

#include <iostream>
#include <algorithm>
#include <array>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

//these varibles can be accessed by any function
const int NEW_DECK[52] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52}; //a standard new deck
const int START_SIZE = 8; //the number of cards in the hand of a player at the start of a game
int deck[52]; //for the deck both players are using
string player1Name = "player1"; //for player1 name
int player1[52]; //player1 hand
int player1Cards = 0; //player1 number of cards
int player1Choice = 0; //player1 card of choice
string player2Name = "player2"; //for player2 name
int player2[52]; //player2 hand
int player2Cards = 0; //player2 number of cards
int player2Choice = 0; //player2 card of choice
int boardCard = 0; //the card on the table
const int NUM_OF_PLAYERS = 2; //number of players
int numOfHumanPlayers = 0; // for the number of human players
int temp = 0; //temp used to sort the cards in a player's hand
int countEligibleCards = 0; //the number of eligible cards a player can play
int countUsedCards = 0; //the number of used cards in the current deck
int cardDraw = 0; //the number of cards a player is drawing
int player1Score = 0; //for player1 score
int player2Score = 0; //for player2 score
bool firstTimeOpeningTheGame = true; //for the welcome message

//function to display the name of a card
void displayCard(int card)
{
	if(card >= 2 && card <= 10)
	{
		cout << "| " << card << " of Hearts |";
	}
	else if(card >= 15 && card <= 23)
	{
		cout << "| " << card - 13 << " of Clubs |";
	}
	else if(card >= 28 && card <= 36)
	{
		cout << "| " << card - 26 << " of Diamonds |";
	}
	else if(card >= 41 && card <= 49)
	{
		cout << "| " << card - 39 << " of Spades |";
	}
	else
	{
		switch(card)
		{
			case 1:
				cout << "| Ace of Hearts |";
				break;
			case 11:
				cout << "| Jack of Hearts |";
				break;
			case 12:
				cout << "| Queen of Hearts |";
				break;
			case 13:
				cout << "| King of Hearts |";
				break;
			case 14:
				cout << "| Ace of Clubs |";
				break;
			case 24:
				cout << "| Jack of Clubs |";
				break;
			case 25:
				cout << "| Queen of Clubs |";
				break;
			case 26:
				cout << "| King of Clubs |";
				break;
			case 27:
				cout << "| Ace of Diamonds |";
				break;
			case 37:
				cout << "| Jack of Diamonds |";
				break;
			case 38:
				cout << "| Queen of Diamonds |";
				break;
			case 39:
				cout << "| King of Diamonds |";
				break;
			case 40:
				cout << "| Ace of Spades |";
				break;
			case 50:
				cout << "| Jack of Spades |";
				break;
			case 51:
				cout << "| Queen of Spades |";
				break;
			case 52:
				cout << "| King of Spades |";
				break;
			default :
				break;
		}
	}
}//end of displayCard

//function to display a deck of cards, or the cards in the hands of players
void displayDeck(int arr[])
{
	for(int i = 0; i < size(NEW_DECK); i++)
	{
		displayCard(arr[i]);
	}
	cout << endl;
}//end of displayDeck

//function to display the cards on the table
void displayTable(int player[], int boardCard)
{
	cout << "\n";
	displayCard(boardCard);
	cout << "\n\n";
	displayDeck(player);
	cout << "\n";
	cout << "------------------------\n";
}//end of displayTable

//function to check if the card is eligible to be played
bool checkCard(int boardCard, int playedCard)
{
	bool check;

	//checking if the card is 8
	if(playedCard == 8 || playedCard == 21 || playedCard == 34 || playedCard == 47)
	{
		check = true;
	}
	//checking if the card has the same number of the board card
	else if(playedCard == boardCard + 13 || playedCard == boardCard + 26 || playedCard == boardCard + 39 || playedCard == boardCard - 13 || playedCard == boardCard - 26 || playedCard == boardCard - 39)
	{
		check = true;
	}
	//chceking if the card is from the same suit as the board card
	else if((boardCard >= 1 && boardCard <= 13) && (playedCard >= 1 && playedCard <= 13))
	{
		check = true;
	}
	else if((boardCard >= 14 && boardCard <= 26) && (playedCard >= 14 && playedCard <= 26))
	{
		check = true;
	}
	else if((boardCard >= 27 && boardCard <= 39) && (playedCard >= 27 && playedCard <= 39))
	{
		check = true;
	}
	else if((boardCard >= 40 && boardCard <= 52) && (playedCard >= 40 && playedCard <= 52))
	{
		check = true;
	}
	else
	{
		check = false;
	}

	return check;
}//end of checkCard

//function to check if any of the cards in the hand of a player are eligible to be played
int checkPlayerCards(int playerCards,int countEligibleCards,int boardCard, int player[])
{
	countEligibleCards = playerCards;

	for(int i = 0; i < playerCards; i++)
	{
	    if(!checkCard(boardCard, player[i]))
	    {
	        countEligibleCards--;
	    }
	}
	
	return countEligibleCards;
}//end of checkPlayerCards

//a function to assign a new deck
void newDeck()
{
	//assigning a new deck
	for(int i = 0; i < size(NEW_DECK); i++)
	{
		deck[i] = NEW_DECK[i];
	}

	//shuffling the deck
	random_shuffle(deck, deck + size(NEW_DECK));
}//end of newDeck

//to sort player1 cards
void sortPlayer1Cards()
{
	for(int i = 0; i < size(NEW_DECK); i++)
	{
	    for(int j = i + 1; j < size(NEW_DECK); j++)
	    {
	        if(player1[i] < player1[j])
    	    {
    	        temp = player1[i];
    	        player1[i] = player1[j];
    	        player1[j] = temp;
    	    }
	    }
	}
}//end of sortPlayer1Cards

//to sort player2 cards
void sortPlayer2Cards()
{
	for(int i = 0; i < size(NEW_DECK); i++)
	{
	    for(int j = i + 1; j < size(NEW_DECK); j++)
	    {
	        if(player2[i] < player2[j])
    	    {
    	        temp = player2[i];
    	        player2[i] = player2[j];
    	        player2[j] = temp;
    	    }
	    }
	}
}//end of sortPlayer2Cards

//function to check the win condition
void checkWin(int player1Cards, int player2Cards)
{
	if(player1Cards == 0)
	{
		cout << "\n" << player1Name << " has won\n\n";
		player1Score += 100;
	}
	else if(player2Cards == 0)
	{
		cout << "\n" << player2Name << " has won\n\n";
		player2Score += 100;
	}
	else
	{
		cout << "Error";
	}
}//end of checkWin

//function to run the game
void playGame()
{
	srand(time(0));

	//creating a new deck
	newDeck();

	//giving each player 8 cards from the shuffled deck
	for(int i = 0; i < START_SIZE * NUM_OF_PLAYERS; i++)
	{
	    if(i < START_SIZE)
	    {
	        player1[i] = deck[i];
    		player1Cards++;
    		countUsedCards++;
	    }
	    else
	    {
	        player2[i - START_SIZE] = deck[i];
	        player2Cards++;
	        countUsedCards++;
	    }
	}

	//sorting the cards of each player
	sortPlayer1Cards();
	sortPlayer2Cards();

	//playing a card into the table
	boardCard = deck[START_SIZE * NUM_OF_PLAYERS];
	countUsedCards++;

	//selecting the number of human players
	do
	{
		cout << "Select 1 for Single Player\n";
		cout << "Select 2 for Multiplayer\n";
		cin >> numOfHumanPlayers;

		if(numOfHumanPlayers < 1 || numOfHumanPlayers > 2)
		{
			cout << "Invalid Selection, Please Try Again\n";
			cin >> numOfHumanPlayers;
		}
	}while(numOfHumanPlayers < 1 || numOfHumanPlayers > 2);

	//taking in players names
	cout << "Please Enter Player1 Name: ";
	cin >> player1Name;

	if(numOfHumanPlayers == 2)
	{
		cout << "Please Enter Player2 Name: ";
		cin >> player2Name;
	}

	//displaying the table with player1 cards
	displayTable(player1,boardCard);
	
	//a loop for the game
	do
	{
		//counting the number of eligible cards in the hands of player1, if none can be played, drawing them another card from the deck (max of 3 cards)
	   do
	   {
	   		countEligibleCards = checkPlayerCards(player1Cards, countEligibleCards, boardCard, player1);
	        
			if(countEligibleCards == 0 )
			{
				player1[player1Cards] = deck[countUsedCards];
				player1Cards++;
				cardDraw++;
				countUsedCards++;

				//assigning a new deck in case the first deck was finished
				if(countUsedCards == 52)
				{
					newDeck();
					countUsedCards = 0;
				}

				//sorting player1 cards in case of a card draw
				sortPlayer1Cards();

				displayTable(player1,boardCard);
			}
	   }while(countEligibleCards == 0 && cardDraw != 3);

	   //doing an eligibility check outside the loop for the final card drawn
	   countEligibleCards = checkPlayerCards(player1Cards, countEligibleCards, boardCard, player1);
	   if(cardDraw != 0)
	   {
	   		cout << player1Name << " drew " << cardDraw << " cards\n";
	   }
	   cardDraw = 0;

	   //if player1 can play a card, asking them to play it
	   if(countEligibleCards != 0)
	   {
	   		//player1 picking a card
	   		do
			{
				cout << player1Name << ", pick a card: ";
				cin >> player1Choice;

				if(!checkCard(boardCard, player1[player1Choice - 1]))
				{
					cout << "You can't play that card\n";
				}

			}while(!checkCard(boardCard, player1[player1Choice - 1]));
			
			player1Cards--;

			//changing the card on the table to the player1's card
			boardCard = player1[player1Choice - 1];
			player1[player1Choice - 1] = 0;

			//sorting player1 cards after they played a card
			sortPlayer1Cards();
	   }

	   //if player1 finished their cards, goto outside the loop
	   if(player1Cards == 0)
	   {
	   		goto endOfLoop;
	   }

   		//displaying the table with player2 cards
	   if(numOfHumanPlayers == 2)
	   {
	   		displayTable(player2,boardCard);
	   }

	   //counting the number of eligible cards in the hands of player2, if none can be played, drawing them another card from the deck (max of 3 cards)
	   do
	   {
	   		countEligibleCards = checkPlayerCards(player2Cards, countEligibleCards, boardCard, player2);
	        
			if(countEligibleCards == 0 )
			{
				player2[player2Cards] = deck[countUsedCards];
				player2Cards++;
				cardDraw++;
				countUsedCards++;

				//assigning a new deck in case the first deck was finished
				if(countUsedCards == 52)
				{
					newDeck();
					countUsedCards = 0;
				}

				//sorting player2 cards in case of a card draw
				sortPlayer2Cards();

				if(numOfHumanPlayers == 2)
				{
					displayTable(player2,boardCard);
				}
			}
	   }while(countEligibleCards == 0 && cardDraw != 3);

	   //doing an eligibility check outside the loop for the final card drawn
	   countEligibleCards = checkPlayerCards(player2Cards, countEligibleCards, boardCard, player2);
	   if(cardDraw != 0)
	   {
	   		cout << player2Name << " drew " << cardDraw << " cards\n";
	   }
	   cardDraw = 0;

	   //if player1 can play a card, asking them to play it
	   if(countEligibleCards != 0)
	   {
	   		//player2 choice of cards depending on if they are a bot or a human
   			do
			{
				if(numOfHumanPlayers == 1)
				{
					player2Choice = rand() % player2Cards + 1;
				}
				else
				{
					cout << player2Name << ", pick a card: ";
					cin >> player2Choice;

					if(!checkCard(boardCard, player2[player2Choice - 1]))
					{
						cout << "You can't play that card\n";
					}
				}
			}while(!checkCard(boardCard, player2[player2Choice - 1]));
			
			player2Cards--;

			boardCard = player2[player2Choice - 1];
			player2[player2Choice - 1] = 0;


	   		//sorting player2 cards after they played a card
			sortPlayer2Cards();
	   }

	   //if player2 finished their cards, goto outside the loop
	   if(player2Cards == 0)
	   {
	   		goto endOfLoop;
	   }

	   //displaying player2 cards
	   if(numOfHumanPlayers == 1)
	   {
	   		cout << "\nPlayer2 has " << player2Cards << " cards\n";
	   }
		displayTable(player1,boardCard);

	}while(player1Cards != 0 && player2Cards != 0);

	//gotp label
	endOfLoop:

	//checking who won
	checkWin(player1Cards,player2Cards);
}//end of playGame

//function to interact with the menu
void menu()
{
	int input; //for player input

	//to display a welcome message and not repeat it everytime the menu is displayed
	if(firstTimeOpeningTheGame)
	{
		cout << "--- WELCOME TO CRAZY8 ---\n\n";
		firstTimeOpeningTheGame = false;
	}

	//the menu
	do
	{
		cout << "1. Play\n";
		cout << "2. Rules\n";
		cout << "3. Leaderboard\n";
		cout << "4. Exit Game\n";
		
		//checking if the input is valid
		do
		{
			cout << "\nPlease select from the menu\n";

			cin >> input;

			if(input < 1 && input > 4)
			{
				cout << "Invalid Option\n\n";
			}

		}while(input < 1 || input > 4);

		switch(input)
		{
			case 1:
				playGame();
				break;
			case 2:
				cout << "Each player starts with 8 cards\n";
				cout << "A player must play a card that matches the suit or number of the card already on the table\n";
				cout << "A player can play an 8 card at anytime\n";
				cout << "If a player does not have any card to play they must draw a new card from the deck up to a maximum of 3 cards\n";
				cout << "The first player who finishs their cards wins\n";
				cout << "100 points are awarded for a win\n";
				cout << "Cards are numbered from left to right 1,2,3...., use the appropriate number when asked to select a card\n------------------\n";
				break;
			case 3:
				if(player2Score > player1Score)
				{
					cout << player2Name << " Score: " << player2Score << "\n" << player1Name << "Score: " << player1Score << "\n------------------\n";
				}
				else
				{
					cout << player1Name << " Score: " << player1Score << "\n" << player2Name << " Score: " << player2Score << "\n------------------\n";
				}
				break;
			case 4:
				cout << "\nThank you for playing CRAZY8\n";
				exit(0);
			default:
				cout << "\nERROR\n";
				break;
		}

	}while(true);
}//end of menu

int main()
{
	menu();
	return 0;
}//end of main