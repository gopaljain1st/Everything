#include<iostream>
#include<conio.h>
#include<fstream>
#include<string>
#include<windows.h>
using namespace std;
void gotoxy(int x, int y)
{
	COORD c;
	c.X=x;
	c.Y=y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),c);
}
class book
{
	public:
	char author[50],title[50],publisher[50];
	float price;
	int stock,id;
	void input()
		{
			gotoxy(26,10);
			cout<<"********** ENTER PUBLISHER NAME **********\n";
			gotoxy(40,11);
			cin.getline(publisher,50);
			system("cls");
			gotoxy(26,10);
			cout<<"********** ENTER BOOK PRICE **********\n";
			gotoxy(43,11);
			cin>>price;
			system("cls");
			gotoxy(26,10);
			cout<<"********** ENTER BOOK STOCK **********\n";
			gotoxy(43,11);
			cin>>stock;
			system("cls");
		}

        	void display()
		{   gotoxy(15,8);
		    cout<<"*********************************************************\n";
		    gotoxy(32,9);
		    cout<<" BOOK TITLE: "<<title<<endl;
			gotoxy(32,11);
			cout<<" AUTHOR: "<<author<<endl;
			gotoxy(32,12);
			cout<<" PUBLISHER: "<<publisher<<endl;
			gotoxy(32,13);
			cout<<" BOOK ID: "<<id<<endl;
			gotoxy(32,14);
			cout<<" PRICE: "<<price<<endl;
			gotoxy(32,15);
			cout<<" STOCK: "<<stock<<endl;
			gotoxy(15,16);
			cout<<"*********************************************************\n";
			getch();
			system("cls");
		}
};
int main()
{
	book obj;
	char author[50],title[50],publisher[50];
	float rs,price;
	int choice,buy,stock,temp=0,location,total_books,count=0,id,ch;
	fstream f1;
	do
	{
	    int i=0;
	    cout<<"\n\n\n\n";
		cout<<"\t\t------------------welcome in jain book store ------------------\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|  => please enter your choice:                               |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|          1: For Enter New Books Collection                  |\n";
		cout<<"\t\t|          2: For issue Book From Store                       |\n";
		cout<<"\t\t|          3: For Checking Details Of Any Book                |\n";
		cout<<"\t\t|          4: Display All Books Information                   |\n";
		cout<<"\t\t|          5: Remove Book From Store                          |\n";
		cout<<"\t\t|          6: To Know Total Books Present In Store            |\n";
		cout<<"\t\t|          7: For Update Any Book Information                 |\n";
		cout<<"\t\t|          8: exit...                                         |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t---------------------------------------------------------------\n";
		       gotoxy(45,17);
		cin>>choice;
		system("cls");
		switch(choice)
		{
			case 1:
				gotoxy(26,10);
				cout<<"********** ENTER BOOK NAME **********\n";
				fflush(stdin);
				gotoxy(40,11);
				cin.getline(title,50);
				system("cls");
		    	gotoxy(26,10);
				cout<<"********** ENTER AUTHOR NAME **********\n";
				gotoxy(40,11);
				cin.getline(author,50);
				system("cls");
				temp=0;
				count=0;
			    f1.open("text3.dat",ios::in|ios::binary);
				f1.seekg(0,ios::beg);
			   	while(f1.read((char*)&obj,sizeof(obj)))
				{
				    count++;
					if(strcmp(obj.author,author)==0&&strcmp(obj.title,title)==0)
					{
							gotoxy(24,11);
							cout<<"----- THIS BOOK IS ALLREADY AVILABLE -----\n";
							temp=1;
					}
				}
				f1.close();
				if(temp==0)
				{
				    f1.open("text3.dat",ios::app|ios::binary);
		     		strcpy(obj.author,author);
					strcpy(obj.title,title);
					obj.input();
					obj.id=count+1;
                    f1.seekp(0,ios::end);
			    	f1.write((char*)&obj,sizeof(obj));
			    	obj.display();
			    	f1.close();
			        gotoxy(22,11);
			               cout<<"----- Collection Of Book Is Edited In store -----\n";
			    }
			    getch();
				system("cls");
				break;
			case 2:
				gotoxy(18,9);
				cout<<"---------------------------------------------------------\n";
				gotoxy(28,11);
				cout<<" Press 1 For issue Using ID \n";
				gotoxy(28,12);
				cout<<" Press 2 For issue Using Name \n";
				gotoxy(18,15);
				cout<<"---------------------------------------------------------\n";
				gotoxy(40,13);
				cin>>ch;
				system("cls");
				if(ch==1)
				{
					gotoxy(28,10);
					cout<<" ----- ENTER BOOK ID -----\n";
					gotoxy(42,11);
					cin>>id;
					system("cls");
					temp=1;
					i=0;
				f1.open("text3.dat",ios::in|ios::out|ios::binary);
				f1.seekg(0,ios::beg);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{  i++;
					if(obj.id==id)
					{
					    temp=0;
					    gotoxy(28,10);
						cout<<" ----- Enter Books Quantity -----\n";
						gotoxy(45,11);
						cin>>buy;
						system("cls");
						if(obj.stock>=buy)
						{
							rs=obj.price*float(buy);
							gotoxy(28,10);
							cout<<" ----- Please Give Me: "<<rs<<"rs ----- \n";
							gotoxy(28,11);
							cout<<" ----- Thank You For Coming -----";
						if(i==1)
						location=0;
						else location=(i-1)*sizeof(obj);
						obj.stock=obj.stock-buy;
						f1.seekp(location);
						f1.write((char*)&obj,sizeof(obj));
						f1.close();
						getch();
						system("cls");
					    }
					   	else
						{
							gotoxy(26,10);
							cout<<" _____ BOOKS ARE NOT IN STOCK _____\n";
	                        getch();
							system("cls");
							f1.close();
						}
				   }

				}
				if(i==0)
				f1.close();
		   }

			else if(ch==2)
			{
				gotoxy(26,10);
				cout<<"********** ENTER BOOK NAME **********\n";
				fflush(stdin);
				gotoxy(40,11);
				cin.getline(title,50);
				system("cls");
				gotoxy(26,10);
			    cout<<"********** ENTER AUTHOR NAME **********\n";
				gotoxy(40,11);
				cin.getline(author,50);
				system("cls");
				temp=1;
				i=0;
				f1.open("text3.dat",ios::in|ios::out|ios::binary);
				f1.seekg(0,ios::beg);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{
					i++;
					if(strcmp(obj.author,author)==0&&strcmp(obj.title,title)==0)
					{	gotoxy(28,10);
						cout<<" ----- Enter Books Quantity -----\n";
						gotoxy(45,11);
						cin>>buy;
						system("cls");
						if(obj.stock>=buy)
						{
							rs=obj.price*float(buy);
							gotoxy(28,10);
							cout<<" ----- Please Give Me: "<<rs<<"rs ----- \n";
							gotoxy(28,11);
							cout<<" ----- Thank You For Coming -----";
						if(i==1)
						location=0;
						else location=(i-1)*sizeof(obj);
						obj.stock=obj.stock-buy;
						f1.seekp(location);
						f1.write((char*)&obj,sizeof(obj));
						f1.close();
						getch();
						system("cls");
				   	}
					else
						{
							gotoxy(26,10);
							cout<<" _____ BOOKS ARE NOT IN STOCK _____\n";
	                        getch();
							system("cls");
							f1.close();
						}
						temp=0;
					}
				}
				if(i==0)
				f1.close();
			}
			else{
			         gotoxy(32,10);
					 cout<<" Opps!!!!! \n";
					 gotoxy(27,11);
					 cout<<"----- Please Enter Coorect Choice -----\n";
			         getch();
			         system  ("cls");
			         temp=0;
			   }
				if(temp==1)
				{
				gotoxy(21,11);
				cout<<"----- There Is No Collection Of This Book -----\n";
				getch();
				}
				system("cls");
				break;
		    case 3:
		    	temp=0;
		    	gotoxy(18,9);
				cout<<"---------------------------------------------------------\n";
				gotoxy(28,11);
				cout<<" Press 1 For Checking Using ID \n";
				gotoxy(28,12);
				cout<<" Press 2 For Checking Using Name \n";
				gotoxy(18,15);
				cout<<"---------------------------------------------------------\n";
				gotoxy(40,13);
				cin>>choice;
				system("cls");
				if(choice==1)
				{
					gotoxy(28,10);
					cout<<" ----- ENTER BOOK ID -----\n";
					gotoxy(42,11);
					cin>>id;
					system("cls");
					temp=0;
				f1.open("text3.dat",ios::in|ios::binary);
				f1.seekg(0,ios::beg);
				while(f1.read((char*)&obj,sizeof(obj)))
				 {
					if(obj.id==id)
					{
						obj.display();
						temp=1;
					}
				 }
				 f1.close();
				if(temp==0)
			    	{
					gotoxy(21,11);
				    cout<<"----- There Is No Collection Of This Book -----\n";
				    getch();
					}
			    	system("cls");
			   }
			   else if(choice==2)
			   {
		    	gotoxy(26,10);
		        cout<<"********** ENTER BOOK NAME **********\n";
		    	fflush(stdin);
		    	gotoxy(40,11);
				cin.getline(title,50);
				system("cls");
				gotoxy(26,10);
			    cout<<"********** ENTER AUTHOR NAME **********\n";
				gotoxy(40,11);
				cin.getline(author,50);
				system("cls");
				temp=0;
				f1.open("text3.dat",ios::in|ios::binary);
				f1.seekg(0,ios::beg);
			while(f1.read((char*)&obj,sizeof(obj)))
				{
					if(strcmp(obj.author,author)==0&&strcmp(obj.title,title)==0)
					 {
						obj.display();
						temp=1;
					 }
		    	}
		    	f1.close();
			    	if(temp==0)
			    	{
					gotoxy(21,11);
				    cout<<"----- There Is No Collection Of This Book -----\n";
				    getch();
					}
					system("cls");
				}
			    else
			    {
			    	 gotoxy(32,10);
					 cout<<" Opps!!!!! \n";
					 gotoxy(27,11);
					 cout<<"----- Please Enter Coorect Choice -----\n";
			         getch();
			         system  ("cls");
				}
					break;
			case 4:
				gotoxy(0,1);
				cout.width(90);
				cout.fill('_');
			    cout<<"_";
			    gotoxy(0,2);
			    cout<<"|      BOOK NAME      |     AUTHOR NAME     |    PUBLISHER NAME    | ID | STOCK | PRICE |"<<endl;
			    gotoxy(0,3);
			    cout.width(89);
			    cout.fill('-');
			    cout<<"-";
			    temp=0;
		        i=3;
			    f1.open("text3.dat",ios::in|ios::binary);
				f1.seekg(0,ios::beg);
			    while(f1.read((char*)&obj,sizeof(obj)))
			    {  i++;
			       gotoxy(1,i);
			       cout<<obj.title;
			       gotoxy(24,i);
			       cout<<obj.author;
			       gotoxy(46,i);
			       cout<<obj.publisher;
			       gotoxy(70,i);
			       cout<<obj.id;
			       gotoxy(75,i);
			       cout<<obj.stock;
			       gotoxy(83,i);
			       cout<<obj.price;
			       temp=1;
			    }
			    f1.close();
			    if(temp==0)
				{
					gotoxy(24,11);
					cout<<"----- There Is No Collection Of Books -----\n";
				}
			    getch();
			    system("cls");
			    break;
			case 5:
				gotoxy(18,9);
				cout<<"---------------------------------------------------------\n";
				gotoxy(28,11);
				cout<<" Press 1 For Removing Using ID \n";
				gotoxy(28,12);
				cout<<" Press 2 For Removing Using Name \n";
				gotoxy(18,15);
				cout<<"---------------------------------------------------------\n";
				gotoxy(40,13);
				cin>>choice;
				system("cls");
				if(choice==1)
				{
					gotoxy(28,10);
					cout<<" ----- ENTER BOOK ID -----\n";
					gotoxy(42,11);
					cin>>id;
					system("cls");
					temp=1;
					i=0;
				f1.open("text3.dat",ios::in|ios::out);
				f1.seekg(0);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{
					i++;
					if(obj.id==id)
					{
						if(obj.price==0&&obj.stock==0)
						{
							f1.close();
							gotoxy(22,11);
							cout<<"----- This Book Is Allready Removed -----\n";
							getch();
							temp=0;
						}
						else
						{
						if(i==1)
						location=0;
						else location=(i-1)*sizeof(obj);
						obj.stock=0;
					    obj.price=0;
					    f1.seekp(location);
						f1.write((char*)&obj,sizeof(obj));
						temp=0;
						f1.close();
						gotoxy(24,11);
						cout<<"----- BOOK IS REMOVED FROM STORE -----\n";
					    }
				   }
			   }
			   if(temp==1)
				{
					gotoxy(24,11);
					cout<<"----- There Is No Collection Of Books -----\n";
					f1.close();
				}
		   }
			   else if(choice==2)
			   {
				gotoxy(26,10);
				cout<<"********** ENTER BOOK NAME **********\n";
				fflush(stdin);
				gotoxy(40,11);
				cin.getline(title,50);
				system("cls");
				gotoxy(26,10);
			    cout<<"********** ENTER AUTHOR NAME **********\n";
				gotoxy(40,11);
				cin.getline(author,50);
				system("cls");
				temp=1;
				i=0;
			    f1.open("text3.dat",ios::in|ios::out);
				f1.seekg(0);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{
					i++;
					if(strcmp(obj.author,author)==0&&strcmp(obj.title,title)==0)
					{
						if(obj.price==0&&obj.stock==0)
						{
							gotoxy(22,11);
							cout<<"----- This Book Is Allready Removed -----\n";
							temp=0;
							f1.close();
						}
						else
						{
						if(i==1)
						location=0;
						else location=(i-1)*sizeof(obj);
						obj.stock=0;
					    obj.price=0;
					    f1.seekp(location);
						f1.write((char*)&obj,sizeof(obj));
						f1.close();
						temp=0;
						gotoxy(24,11);
						cout<<"----- BOOK IS REMOVED FROM STORE -----\n";
					    }
					}
				}
			if(temp==1)
				{
			    gotoxy(20,11);
				cout<<"----- There Is No Collection Of This Book -----\n";
				f1.close();
				}
		    }
		    else
		    {
		    	     gotoxy(32,10);
					 cout<<" Opps!!!!! \n";
					 gotoxy(27,11);
					 cout<<"----- Please Enter Coorect Choice -----\n";
			         getch();
			         system  ("cls");
			}
				getch();
				system("cls");
				break;
			case 6:
				total_books=0;
				f1.open("text3.dat",ios::in|ios::binary);
				f1.seekg(0);
			    while(f1.read((char*)&obj,sizeof(obj)))
			    total_books+=obj.stock;
			    f1.close();
			    gotoxy(27,10);
				cout<<"      TOTAL BOOKS IN STORE      \n";
				gotoxy(43,11);
				cout<<total_books<<"\n";
				getch();
				system("cls");
				break;
			case 7: gotoxy(18,9);
				cout<<"---------------------------------------------------------\n";
				gotoxy(28,11);
				cout<<" Press 1 For updating Using ID \n";
				gotoxy(28,12);
				cout<<" Press 2 For updating Using Name \n";
				gotoxy(18,15);
				cout<<"---------------------------------------------------------\n";
				gotoxy(40,13);
				cin>>choice;
				system("cls");
				if(choice==1)
				{
					gotoxy(28,10);
					cout<<" ----- ENTER BOOK ID -----\n";
					gotoxy(42,11);
					cin>>id;
					system("cls");
					temp=1;
				f1.open("text3.dat",ios::in|ios::out);
				f1.seekg(0,ios::beg);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{
					if(obj.id==id)
					{
						temp=0;
						f1.close();
						do
						{
							cout<<"\n\n\n\n";
		cout<<"\t\t---------------------------------------------------------------\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|               1: For Update Book Name                       |\n";
		cout<<"\t\t|               2: For Update Author Name                     |\n";
		cout<<"\t\t|               3: For Update Publisher Name                  |\n";
		cout<<"\t\t|               4: For Update Book Price                      |\n";
		cout<<"\t\t|               5: For Update Stock Name                      |\n";
		cout<<"\t\t|               6: For Back To Main Menu                      |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t---------------------------------------------------------------\n";
		gotoxy(45,15);
		cin>>ch;
		system("cls");
		        switch(ch)
		        {
		        	case 1: gotoxy(24,10);
				            cout<<"********** ENTER NEW BOOK NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(title,50);
				            system("cls");
				            i=0;
				            temp=1;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				           	while(f1.read((char*)&obj,sizeof(obj))&&temp)
				           {
						         i++;
						         if(obj.id==id)
					             {
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						          strcpy(obj.title,title);
						          f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						          obj.display();
								  temp=1;
						         }
						   }
						    f1.close();
						    gotoxy(24,11);
						    cout<<"----- BOOK NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
			                system("cls");
						    break;
					case 2:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW AUTHOR NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(author,50);
				            system("cls");
				            i=0;
				            temp=1;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				           	while(f1.read((char*)&obj,sizeof(obj))&&temp)
				           {	i++;
						         if(obj.id==id)
					             {
				                  strcpy(obj.author,author);
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						         f1.write((char*)&obj,sizeof(obj));
						         obj.display();
						         temp=0;
						         }
						   }f1.close();
						    gotoxy(24,11);
						    cout<<"----- AUTHOR NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
							break;
					case 3:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW PUBLISHER NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(publisher,50);
				            system("cls");
				            i=0;
				            temp=1;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				             	while(f1.read((char*)&obj,sizeof(obj))&&temp)
				           {	i++;
						         if(obj.id==id)
					             {
				                  strcpy(obj.publisher,publisher);
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						        f1.write((char*)&obj,sizeof(obj));
						        obj.display();
						        temp=0;
						         }
						   }f1.close();
						    gotoxy(24,11);
						    cout<<"----- PUBLISHER NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 4:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW PRICE OF BOOK **********\n";
				            gotoxy(40,11);
				            cin>>price;
				            system("cls");
				            i=0;
				            temp=1;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				            while(f1.read((char*)&obj,sizeof(obj))&&temp)
				           {	i++;
						         if(obj.id==id)
					             {
				                  obj.price=price;
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						          obj.display();
						          temp=0;
						         }
					       }f1.close();
					        gotoxy(24,11);
						    cout<<"----- BOOK PRICE IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 5:
					        gotoxy(24,10);
				            cout<<"********** UPDATE STOCK OF BOOK **********\n";
				            gotoxy(40,11);
				            cin>>stock;
				            system("cls");
				             i=0;
				            temp=1;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				          	while(f1.read((char*)&obj,sizeof(obj))&&temp)
				           {	 i++;
						         if(obj.id==id)
					             {
				                  obj.stock=stock;
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						          obj.display();
						          temp=0;
						         }
				           }f1.close();
						   gotoxy(24,11);
						    cout<<"----- BOOK STOCK IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 6:  break;
				    default :
					           gotoxy(32,10);
					           cout<<" Opps!!!!! \n";
					           gotoxy(27,11);
						       cout<<"----- Please Enter Coorect Choice -----\n";
			                   getch();
			                   system  ("cls");
	                    	}
	           	        }while(ch!=6);
				}
			}
			if(temp==1)
			{
				 gotoxy(27,11);
			    cout<<"----- Please Enter Valid ID No -----\n";
			    getch();
               system  ("cls");
               f1.close();
			}
		  }
		  else if(choice==2)
		  {
		  	    gotoxy(26,10);
				cout<<"********** ENTER BOOK NAME **********\n";
				fflush(stdin);
				gotoxy(40,11);
				cin.getline(title,50);
				system("cls");
				gotoxy(26,10);
			    cout<<"********** ENTER AUTHOR NAME **********\n";
				gotoxy(40,11);
				cin.getline(author,50);
				system("cls");
				temp=1;
			    f1.open("text3.dat",ios::in|ios::out);
				f1.seekg(0);
				while(f1.read((char*)&obj,sizeof(obj))&&temp)
				{
					if(strcmp(obj.author,author)==0&&strcmp(obj.title,title)==0)
					{
					   	id=obj.id;
					     f1.close();
					     temp=0;
						do
						{
							cout<<"\n\n\n\n";
		cout<<"\t\t---------------------------------------------------------------\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|               1: For Update Book Name                       |\n";
		cout<<"\t\t|               2: For Update Author Name                     |\n";
		cout<<"\t\t|               3: For Update Publisher Name                  |\n";
		cout<<"\t\t|               4: For Update Book Price                      |\n";
		cout<<"\t\t|               5: For Update Stock Name                      |\n";
		cout<<"\t\t|               6: For Back To Main Menu                      |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t|                                                             |\n";
		cout<<"\t\t---------------------------------------------------------------\n";
		gotoxy(45,15);
		cin>>ch;
		system("cls");
		        switch(ch)
		        {
		        	case 1: gotoxy(24,10);
				            cout<<"********** ENTER NEW BOOK NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(title,50);
				            system("cls");
				            i=0;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				           	while(f1.read((char*)&obj,sizeof(obj)))
				           {
						         i++;
						         if(obj.id==id)
					             {
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						          strcpy(obj.title,title);
						          f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						         }
						   }f1.close();
						    gotoxy(24,11);
						    cout<<"----- BOOK NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
			                system("cls");
						    break;
					case 2:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW AUTHOR NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(author,50);
				            system("cls");
				            i=0;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				           	while(f1.read((char*)&obj,sizeof(obj)))
				           {	i++;
						         if(obj.id==id)
					             {
				                  strcpy(obj.author,author);
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						         f1.write((char*)&obj,sizeof(obj));
						         }
						   }f1.close();
						    gotoxy(24,11);
						    cout<<"----- AUTHOR NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
							break;
					case 3:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW PUBLISHER NAME **********\n";
				           	fflush(stdin);
				            gotoxy(40,11);
				            cin.getline(publisher,50);
				            system("cls");
				            i=0;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				             	while(f1.read((char*)&obj,sizeof(obj)))
				           {
						         if(obj.id==id)
					             { i++;
				                  strcpy(obj.publisher,publisher);
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						        f1.write((char*)&obj,sizeof(obj));
						         }
						   }f1.close();
						    gotoxy(24,11);
						    cout<<"----- PUBLISHER NAME IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 4:
					        gotoxy(24,10);
				            cout<<"********** ENTER NEW PRICE OF BOOK **********\n";
				            gotoxy(40,11);
				            cin>>price;
				            system("cls");
				            i=0;
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				            while(f1.read((char*)&obj,sizeof(obj)))
				           {	i++;
						         if(obj.id==id)
					             {
				                  obj.price=price;
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						         }
					       }f1.close();
						    gotoxy(24,11);
						    cout<<"----- BOOK PRICE IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 5:
					        gotoxy(24,10);
				            cout<<"********** UPDATE STOCK OF BOOK **********\n";
				            gotoxy(40,11);
				            cin>>stock;
				            system("cls");
				            f1.open("text3.dat",ios::in|ios::out);
				            f1.seekg(0);
				            i=0;
				          	while(f1.read((char*)&obj,sizeof(obj)))
				           {	i++;
						         if(obj.id==id)
					             {
				                  obj.stock=stock;
				                  if(i==1)
						          location=0;
						          else location=(i-1)*sizeof(obj);
						           f1.seekp(location);
						          f1.write((char*)&obj,sizeof(obj));
						         }
				           }f1.close();
						   gotoxy(24,11);
						    cout<<"----- BOOK STOCK IS UPDATED SUCCESFULLY -----\n";
						    getch();
						    system("cls");
						    break;
					case 6: break;
				    default :  gotoxy(32,10);
					           cout<<" Opps!!!!! \n";
					           gotoxy(27,11);
						       cout<<"----- Please Enter Coorect Choice -----\n";
			                   getch();
			                   system  ("cls");
	                    	}
	               }while(ch!=6);
				}
			}
			if(temp==1)
			{
				 gotoxy(20,11);
				cout<<"----- There Is No Collection Of This Book -----\n";
			getch();
               system  ("cls");
               f1.close();
			}
		  }
		   else
		    {
		    	     gotoxy(32,10);
					 cout<<" Opps!!!!! \n";
					 gotoxy(27,11);
					 cout<<"----- Please Enter Coorect Choice -----\n";
			         getch();
			         system  ("cls");
			}
			     case 8: break;
		      default : gotoxy(32,10);
					 cout<<" Opps!!!!! \n";
					 gotoxy(27,11);
					 cout<<"----- Please Enter Coorect Choice -----\n";
			         getch();
			         system  ("cls");
    }
}while(choice!=8);
	system("cls");
	gotoxy(23,10);
	cout<<"------ Thank You For Using Our System -----\n";
	gotoxy(23,11);
	cout<<"             press enter to exit            \n";
	getch();
	gotoxy(0,20);
}
