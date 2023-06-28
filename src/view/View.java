package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class View extends Application {
	
	String selectedHero;
	static Hero startGameHero;
	static Hero selectedHeroDuringGame= null;
	static String name ="";
	static String type="";
	static int hp=0;
	static int actions=0;
	static int damage=0;
	static int vaccineAmount = 0;
	static int supplyAmount =0;
	static boolean choosingTarget = false;
	static model.characters.Character target = null;
	static int targetHp = 0;
	static String targetName = "";
	static boolean gameEnded = false;
	static boolean gameWon = false;
	
	
	
	public void start(Stage arg0) throws Exception {
		
		///////START GAME SCENE//////////////
		
		BorderPane root = new BorderPane();
		
		
		// TODO Auto-generated method stub
		Label selected = new Label("Selected");
		selected.setStyle("fx-fill: green");
		//min width and height
		arg0.setMinHeight(700);
		arg0.setMinWidth(1200);
//		arg0.setMaxHeight(700);
//		arg0.setMaxWidth(1200);
		arg0.setResizable(false);
		
		
		//starting screen scene
		HBox hboxtop = addHBox();
		HBox hboxbottom = addHBox();
		VBox vboxleft = addVBox();
		VBox vboxright = addVBox();
		vboxleft.setStyle("-fx-background-color: black");
		vboxleft.setMinWidth(200);
		vboxright.setStyle("-fx-background-color:black");
		vboxright.setMinWidth(200);
		FlowPane flow = new FlowPane();
		
		//buttons
	    //MAKE CARD-LIKE CONTAINER THAT CONTAINS BUTTON PICTURE AND TRAITS FOR EACH HERO
		
		
		GridPane JoelCard = new GridPane();
		JoelCard.setStyle("-fx-border-color: black");
		Button Joel = new Button("Joel Miller");
		Joel.setOnAction(e -> {
			selectedHero = "Joel Miller";
		});
		Joel.setPrefWidth(100);
		flow.setMargin(Joel, new Insets(15, 12, 15, 12));
		Label JoelLabel = new Label("Fighter" +"\n"+"140 HP"+"\n"+ "5 MaxActions"+"\n"+"30 attackDmg");
		JoelCard.setRowIndex(Joel,0);
		JoelCard.setRowIndex(JoelLabel, 1);
		JoelCard.setMargin(Joel, new Insets(15, 12, 15, 12));
		JoelLabel.setPadding(new Insets(15, 12, 15, 12));
		JoelCard.getChildren().addAll(Joel,JoelLabel);

		
		
		GridPane EllieCard = new GridPane();
		Button Ellie = new Button("Ellie Williams");
		Ellie.setOnAction(e -> {
			selectedHero = "Ellie Williams";
		});
		Ellie.setPrefWidth(100);
		flow.setMargin(Ellie, new Insets(15, 12, 15, 12));
		Label EllieLabel = new Label("Medic" +"\n"+"110 HP"+"\n"+ "6 MaxActions"+"\n"+"15 attackDmg");
		EllieCard.setRowIndex(Ellie,0);
		EllieCard.setRowIndex(EllieLabel, 1);
		EllieCard.setMargin(Ellie, new Insets(15, 12, 15, 12));
		EllieLabel.setPadding(new Insets(15, 12, 15, 12));
		EllieCard.setStyle("-fx-border-color: black");
		EllieCard.getChildren().addAll(Ellie,EllieLabel);
		
		
		GridPane TessCard = new GridPane();
		Button Tess = new Button("Tess");
		Tess.setOnAction(e -> {
			selectedHero = "Tess";
		});
		Tess.setPrefWidth(100);
		flow.setMargin(Tess, new Insets(15, 12, 15, 12));
		Label TessLabel = new Label("Explorer" +"\n"+"80 HP"+"\n"+ "6 MaxActions"+"\n"+"20 attackDmg");
		TessCard.setRowIndex(Tess,0);
		TessCard.setRowIndex(TessLabel, 1);
		TessCard.setMargin(Tess, new Insets(15, 12, 15, 12));
		TessLabel.setPadding(new Insets(15, 12, 15, 12));
		TessCard.setStyle("-fx-border-color: black");
		TessCard.getChildren().addAll(Tess,TessLabel);
		
		
		GridPane RileyCard = new GridPane();
		Button Riley = new Button("Riley Abel");
		Riley.setOnAction(e -> {
			selectedHero = "Riley Abel";
		});
		Riley.setPrefWidth(100);
		flow.setMargin(Riley, new Insets(15, 12, 15, 12));
		Label RileyLabel = new Label("Explorer" +"\n"+"90 HP"+"\n"+ "5 MaxActions"+"\n"+"20 attackDmg");
		RileyCard.setRowIndex(Riley,0);
		RileyCard.setRowIndex(RileyLabel, 1);
		RileyCard.setMargin(Riley, new Insets(15, 12, 15, 12));
		RileyLabel.setPadding(new Insets(15, 12, 15, 12));
		RileyCard.setStyle("-fx-border-color: black");
		RileyCard.getChildren().addAll(Riley,RileyLabel);
		
		
		GridPane TommyCard = new GridPane();
		Button Tommy = new Button("Tommy Miller");
		Tommy.setOnAction(e -> {
			selectedHero = "Tommy Miller";
		});
		Tommy.setPrefWidth(100);
		flow.setMargin(Tommy, new Insets(15, 12, 15, 12));
		Label TommyLabel = new Label("Explorer" +"\n"+"95 HP"+"\n"+ "5 MaxActions"+"\n"+"25 attackDmg");
		TommyCard.setRowIndex(Tommy,0);
		TommyCard.setRowIndex(TommyLabel, 1);
		TommyCard.setMargin(Tommy, new Insets(15, 12, 15, 12));
		TommyLabel.setPadding(new Insets(15, 12, 15, 12));
		TommyCard.setStyle("-fx-border-color: black");
		TommyCard.getChildren().addAll(Tommy,TommyLabel);
		
		
		GridPane BillCard = new GridPane();
		Button Bill = new Button("Bill");
		Bill.setOnAction(e -> {
			selectedHero = "Bill";
		});
		Bill.setPrefWidth(100);
		flow.setMargin(Bill, new Insets(15, 12, 15, 12));
		Label BillLabel = new Label("Medic" +"\n"+"100 HP"+"\n"+ "7 MaxActions"+"\n"+"10 attackDmg");
		BillCard.setRowIndex(Bill,0);
		BillCard.setRowIndex(BillLabel, 1);
		BillCard.setMargin(Bill, new Insets(15, 12, 15, 12));
		BillLabel.setPadding(new Insets(15, 12, 15, 12));
		BillCard.setStyle("-fx-border-color: black");
		BillCard.getChildren().addAll(Bill,BillLabel);
		
		GridPane DavidCard = new GridPane();
		Button David = new Button("David");
		David.setOnAction(e -> {
			selectedHero = "David";
		});
		David.setPrefWidth(100);
		flow.setMargin(David, new Insets(15, 12, 15, 12));
		Label DavidLabel = new Label("Fighter" +"\n"+"150 HP"+"\n"+ "4 MaxActions"+"\n"+"35 attackDmg");
		DavidCard.setRowIndex(David,0);
		DavidCard.setRowIndex(DavidLabel, 1);
		DavidCard.setMargin(David, new Insets(15, 12, 15, 12));
		DavidLabel.setPadding(new Insets(15, 12, 15, 12));
		DavidCard.setStyle("-fx-border-color: black");
		DavidCard.getChildren().addAll(David,DavidLabel);
		
		GridPane HenryCard = new GridPane();
		Button Henry = new Button("Henry Burell");
		Henry.setOnAction(e -> {
			selectedHero = "Henry Burell";
		});
		Henry.setPrefWidth(100);
		flow.setMargin(Henry, new Insets(15, 12, 15, 12));
		Label HenryLabel = new Label("Medic" +"\n"+"105 HP"+"\n"+ "6 MaxActions"+"\n"+"15 attackDmg");
		HenryCard.setRowIndex(Henry,0);
		HenryCard.setRowIndex(HenryLabel, 1);
		HenryCard.setMargin(Henry, new Insets(15, 12, 15, 12));
		HenryLabel.setPadding(new Insets(15, 12, 15, 12));
		HenryCard.setStyle("-fx-border-color: black");
		HenryCard.getChildren().addAll(Henry,HenryLabel);
		
		
		
		//TITLE
		Label Title = new Label("THE LAST OF US - LEGACY");
		Title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
		
		
		flow.setPadding(new Insets(200, 0, 30, 100)); 
		flow.getChildren().addAll(Title,JoelCard,EllieCard,TessCard,RileyCard,TommyCard,BillCard,DavidCard,HenryCard);
		
		Button startButton = new Button("START GAME");
		startButton.setPrefWidth(150);
		startButton.setPadding(new Insets(30, 30, 30, 30)); 
		flow.getChildren().add(startButton);
		
		
		//start button logic
		startButton.setOnAction(e->{
			if (selectedHero == null) {
				 Alert a = new Alert(AlertType.WARNING);
				 a.setHeaderText("No hero selected");
				 a.setContentText("Please select a hero");
				 a.show();
			}
			else {
				try{
					Game.loadHeroes("Heroes.csv");
				}catch(IOException e3) {
					e3.printStackTrace();
				}
				switch (selectedHero) {
				
				case "Joel Miller":
					startGameHero = new Fighter("Joel Miller",140,30,5);
					Game.availableHeroes.remove(0);
					break;
				case "Ellie Williams":
					startGameHero = new Medic("Ellie Williams",110,15,6);
					Game.availableHeroes.remove(1);
					break;
				case "Tess":
					startGameHero = new Explorer("Tess",80,20,6);
					Game.availableHeroes.remove(2);
					break;
				case "Riley Abel":
					startGameHero = new Explorer("Riley Abel",90,25,5);
					Game.availableHeroes.remove(3);
					break;
				case "Tommy Miller":
					startGameHero = new Explorer("Tommy Miller",95,25,5);
					Game.availableHeroes.remove(4);
					break;
				case "Bill":
					startGameHero = new Medic("Bill",100,10,7);
					Game.availableHeroes.remove(5);
					break;
				case "David":
					startGameHero = new Fighter("David",110,15,6);
					Game.availableHeroes.remove(6);
					break;
				case "Henry Burell":
					startGameHero = new Medic("Henry Burell",105,15,6);
					Game.availableHeroes.remove(7);
					break;
				default:
					startGameHero = null;
				}
				scene2(root,arg0);
			}
		});
		///////START GAME SCENE//////////////
		
		Scene sc1 = new Scene(flow,1200,700);
		
		
		arg0.setTitle("THE LAST OF US LEGACY");
		arg0.setScene(sc1);
		arg0.show();
		
		
		//actual game
		
	}
	
	
	
	public static void scene2(BorderPane sc2,Stage primaryStage) {
		try {
			Game.startGame(startGameHero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BorderPane root = sc2;
		GridPane map = new GridPane();
		VBox vboxleft = new VBox();
		HBox hboxbottom = new HBox();
		VBox vboxright = new VBox();
		
		//BUTTONS
		GridPane heroInfo = new GridPane();
		GridPane targetInfo = new GridPane();
		Button attack = new Button("Attack");
		Button chooseTarget = new Button("Set Target");
		Button useSpecial = new Button("Use Special");
		Button cure = new Button("cure");
		Button endTurn = new Button("End Turn");
		Button moveUp = new Button("Up");
		Button moveDown = new Button("Down");
		Button moveLeft = new Button("Left");
		Button moveRight = new Button("Right");
		Pane blank = new Pane();
		GridPane moveButtons = new GridPane();
		GridPane actionButtons = new GridPane();
		blank.setMinHeight(75);
		blank.setMinWidth(75);
		
		Tooltip chooseTargetTip = new Tooltip("press this button then press the desired cell");
		chooseTarget.setTooltip(chooseTargetTip);
		chooseTargetTip.setShowDelay(Duration.seconds(0.1));
		
		chooseTarget.setOnAction(e->{
			choosingTarget = true;
		});
		
		cure.setOnAction(e->{
			try {
				selectedHeroDuringGame.setTarget(target);
				selectedHeroDuringGame.cure();
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				Alert a = new Alert(AlertType.WARNING);
				 a.setHeaderText("Exception");
				 a.setContentText(e1.getMessage());
				 a.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		
		useSpecial.setOnAction(e->{
			try {
				selectedHeroDuringGame.setTarget(target);
				selectedHeroDuringGame.useSpecial();
				updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NoAvailableResourcesException | InvalidTargetException e1) {
				Alert a = new Alert(AlertType.WARNING);
				 a.setHeaderText("Exception");
				 a.setContentText(e1.getMessage());
				 a.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		
		attack.setOnAction(e->{
			try {
				selectedHeroDuringGame.setTarget(target);
				selectedHeroDuringGame.attack();
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (NotEnoughActionsException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				Alert a = new Alert(AlertType.WARNING);
				 a.setHeaderText("Exception");
				 a.setContentText(e1.getMessage());
				 a.show();
			}
			if(target !=null) {
				targetHp = target.getCurrentHp();
			}
			
			if(targetHp>0) {
				targetInfo.getChildren().clear();
				targetName = target.getName();
				Label targname = new Label("Name: "+targetName+"\n");
				Label targHp = new Label("HP: "+targetHp+"\n");
				targetInfo.getChildren().clear();
				targetInfo.setRowIndex(targname, 0);
				targetInfo.setRowIndex(targHp, 1);
				targetInfo.getChildren().addAll(targname,targHp);
				vboxright.getChildren().remove(targetInfo);
				vboxright.getChildren().add(targetInfo);
			}
			else {
				target = null;
				targetInfo.getChildren().clear();
			}
			heroInfo.getChildren().clear();
			updateHeroInfo(heroInfo);
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		
		endTurn.setOnAction(e->{
			try {
				Game.endTurn();
				for (Node node : moveButtons.getChildren()) {
					node.setDisable(true);
				}
				for(Node node : vboxright.getChildren()) {
					node.setDisable(true);
				}
				heroInfo.setDisable(false);
				selectedHeroDuringGame = null;
				target = null;
				
				selectedHeroDuringGame= null;
				name ="";
				type="";
				hp=0;
				actions=0;
				damage=0;
				vaccineAmount = 0;
				supplyAmount =0;
				target = null;
				targetHp = 0;
				targetName = "";
				updateMap(map,vboxright,targetInfo,heroInfo);
				updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				
			} catch (NotEnoughActionsException | InvalidTargetException e1) {
				
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		
		
		moveUp.setOnAction(e->{
			int beforeHp = selectedHeroDuringGame.getCurrentHp();
			try {
				selectedHeroDuringGame.move(Direction.UP);
				
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText(e1.getMessage());
				moveAlert.show();
			}
			heroInfo.getChildren().clear();
			updateHeroInfo(heroInfo);
			if(beforeHp != selectedHeroDuringGame.getCurrentHp()) {
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText("TRAP!");
				moveAlert.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		moveDown.setOnAction(e->{
			int beforeHp = selectedHeroDuringGame.getCurrentHp();
			try {
				selectedHeroDuringGame.move(Direction.DOWN);
				
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText(e1.getMessage());
				moveAlert.show();
			}
			heroInfo.getChildren().clear();
			updateHeroInfo(heroInfo);
			if(beforeHp != selectedHeroDuringGame.getCurrentHp()) {
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText("TRAP!");
				moveAlert.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		moveRight.setOnAction(e->{
			int beforeHp = selectedHeroDuringGame.getCurrentHp();
			try {
				selectedHeroDuringGame.move(Direction.RIGHT);
				
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText(e1.getMessage());
				moveAlert.show();
			}
			heroInfo.getChildren().clear();
			updateHeroInfo(heroInfo);
			if(beforeHp != selectedHeroDuringGame.getCurrentHp()) {
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText("TRAP!");
				moveAlert.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		moveLeft.setOnAction(e->{
			int beforeHp = selectedHeroDuringGame.getCurrentHp();
			try {
				selectedHeroDuringGame.move(Direction.LEFT);
				
				try {
					updateMap(map,vboxright,targetInfo,heroInfo);
					updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText(e1.getMessage());
				moveAlert.show();
			}
			heroInfo.getChildren().clear();
			updateHeroInfo(heroInfo);
			if(beforeHp != selectedHeroDuringGame.getCurrentHp()) {
				Alert moveAlert = new Alert(AlertType.WARNING);
				moveAlert.setContentText("TRAP!");
				moveAlert.show();
			}
			gameEnded = Game.checkGameOver();
			gameWon = Game.checkWin();
			if(gameEnded || gameWon) {
				scene3(root,primaryStage,gameWon);
			}
		});
		
		
		
		
		
		moveButtons.setRowIndex(moveUp, 0);
		moveButtons.setRowIndex(moveLeft, 1);
		moveButtons.setRowIndex(blank, 1);
		moveButtons.setRowIndex(moveRight, 1);
		moveButtons.setRowIndex(moveDown, 2);
		moveButtons.setColumnIndex(moveUp,1);
		moveButtons.setColumnIndex(moveLeft,0);
		moveButtons.setColumnIndex(blank, 1);
		moveButtons.setColumnIndex(moveRight,2);
		moveButtons.setColumnIndex(moveDown,1);
		moveUp.setMinWidth(75);
		moveUp.setMinHeight(75);
		moveDown.setMinWidth(75);
		moveDown.setMinHeight(75);
		moveLeft.setMinWidth(75);
		moveLeft.setMinHeight(75);
		moveRight.setMinWidth(75);
		moveRight.setMinHeight(75);
		moveButtons.getChildren().addAll(moveUp,moveLeft,blank,moveRight,moveDown);
		moveUp.setDisable(true);
		moveDown.setDisable(true);
		moveLeft.setDisable(true);
		moveRight.setDisable(true);
		chooseTarget.setDisable(true);
		endTurn.setDisable(true);
		attack.setDisable(true);
		useSpecial.setDisable(true);
		cure.setDisable(true);
		vboxleft.getChildren().add(moveButtons);
		vboxleft.setAlignment(Pos.CENTER);
		moveButtons.setAlignment(Pos.CENTER);
		chooseTarget.setMinWidth(180);
		chooseTarget.setMinHeight(20);
		attack.setMinWidth(180);
		attack.setMinHeight(20);
		useSpecial.setMinWidth(180);
		useSpecial.setMinHeight(20);
		cure.setMinWidth(180);
		cure.setMinHeight(20);
		endTurn.setMinWidth(180);
		endTurn.setMinHeight(180);
		vboxright.setMargin(cure, new Insets(15, 12, 15, 12));
		vboxright.setMargin(useSpecial, new Insets(15, 12, 15, 12));
		vboxright.setMargin(attack, new Insets(15, 12, 15, 12));
		vboxright.setMargin(chooseTarget, new Insets(15, 12, 15, 12));
		vboxright.setMargin(endTurn, new Insets(15, 12, 15, 12));
		
		vboxright.setAlignment(Pos.CENTER);
		
		vboxright.getChildren().add(attack);
		
		vboxright.getChildren().add(chooseTarget);
		vboxright.getChildren().add(useSpecial);
		vboxright.getChildren().add(cure);
		vboxright.getChildren().add(endTurn);
		vboxleft.setMinWidth(325);
		vboxright.setMinWidth(325);
		hboxbottom.setMinHeight(200);
		hboxbottom.setStyle("-fx-border-color:black");
		hboxbottom.setAlignment(Pos.BASELINE_CENTER);
		
		try {
			updateMap(map,vboxright,targetInfo,heroInfo);
			updateHeroes(hboxbottom,map,moveButtons,vboxright,heroInfo,vboxright,targetInfo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.setCenter(map);
		root.setLeft(vboxleft);
		root.setRight(vboxright);
		root.setBottom(hboxbottom);
		Scene scene2 = new Scene(sc2,1400,800);
		primaryStage.setScene(scene2);
//		primaryStage.setFullScreen(true);
	}
	
	public static void updateMap(GridPane map,VBox vboxright,GridPane targetInfo,GridPane heroInfo) throws FileNotFoundException {
		map.getChildren().clear();
		vboxright.getChildren().remove(heroInfo);
		for (int i=0; i<15;i++) {
			for (int j =0;j<15;j++) {
				final int iSecond = i;
				final int jSecond = j;
				StackPane cellView = new StackPane();
				cellView.setOnMouseClicked(e->{
					if(choosingTarget) {
						if(Game.map[iSecond][jSecond] instanceof CharacterCell) {
							choosingTarget = false;
							target = ((CharacterCell)Game.map[iSecond][jSecond]).getCharacter();
							if(target!=null) {
								targetHp = target.getCurrentHp();
								targetName = target.getName();
								Label targname = new Label("Name: "+targetName+"\n");
								Label targHp = new Label("HP: "+targetHp+"\n");
								targetInfo.getChildren().clear();
								targetInfo.setRowIndex(targname, 0);
								targetInfo.setRowIndex(targHp, 1);
								targetInfo.getChildren().addAll(targname,targHp);
								vboxright.getChildren().remove(targetInfo);
								vboxright.getChildren().add(targetInfo);
								
								cellView.setStyle("-fx-border-color:brown;");
								
								PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
								pause.setOnFinished(event -> {
						                // Reset the button appearance after effect
						             
						                cellView.setStyle("-fx-background-color: white; -fx-border-color: black;");
						            });
								pause.play();
							}
							
						}
						
					}
				});
				cellView.setMinHeight(45);
				cellView.setMinWidth(45);
				cellView.setStyle("-fx-border-color: grey");
				map.setRowIndex(cellView, 14-i);
				map.setColumnIndex(cellView, j);
				map.getChildren().add(cellView);
				if(Game.map[i][j].isVisible()==false) {
					cellView.setStyle("-fx-border-color: grey;-fx-background-color: black;");
				}
				if(Game.map[i][j] instanceof CharacterCell && Game.map[i][j].isVisible()) {
					if (((CharacterCell)Game.map[i][j]).getCharacter() instanceof Zombie) {
						FileInputStream inputstream = new FileInputStream("images\\zombie.jpg"); 
						Image image = new Image(inputstream); 
						ImageView imageview = new ImageView(image);
						imageview.setFitWidth(45);
						imageview.setFitHeight(45);
						cellView.getChildren().add(imageview);
						
					}
					else if (((CharacterCell)Game.map[i][j]).getCharacter() instanceof Hero) {
						String mapHero = ((CharacterCell)Game.map[i][j]).getCharacter().getName();
						Image image = null; 
						switch (mapHero) {
						
						case "Joel Miller":
							FileInputStream inputstream = new FileInputStream("images\\joelpixel.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Ellie Williams":
							inputstream = new FileInputStream("images\\elliepixel.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Riley Abel":
							inputstream = new FileInputStream("images\\riley.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Tess":
							inputstream = new FileInputStream("images\\tess.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Tommy Miller":
							inputstream = new FileInputStream("images\\tommy.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Bill":
							inputstream = new FileInputStream("images\\bill.jpg"); 
							image = new Image(inputstream); 
							break;
						case "David":
							inputstream = new FileInputStream("images\\david.jpg"); 
							image = new Image(inputstream); 
							break;
						case "Henry Burell":
							inputstream = new FileInputStream("images\\henry.jpg"); 
							image = new Image(inputstream); 
							break;
						default:
							mapHero = null;
						}
						
						ImageView imageview = new ImageView(image);
						imageview.setFitWidth(45);
						imageview.setFitHeight(45);
						cellView.getChildren().add(imageview);
						
					}
				}
				else if(Game.map[i][j] instanceof CollectibleCell && Game.map[i][j].isVisible()) {
					if (((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Vaccine) {
						FileInputStream inputstream = new FileInputStream("images\\vaccine.jpg"); 
						Image image = new Image(inputstream); 
						ImageView imageview = new ImageView(image);
						imageview.setFitWidth(45);
						imageview.setFitHeight(45);
						cellView.getChildren().add(imageview);
						
					}
					else {
						FileInputStream inputstream = new FileInputStream("images\\supply.jpg"); 
						Image image = new Image(inputstream); 
						ImageView imageview = new ImageView(image);
						imageview.setFitWidth(45);
						imageview.setFitHeight(45);
						cellView.getChildren().add(imageview);
					}
			}}
			
		}
		if(selectedHeroDuringGame!=null) {
			if(selectedHeroDuringGame.getCurrentHp()!=0) {
				updateHeroInfo(heroInfo);
				vboxright.getChildren().add(heroInfo);
			}
			else {
				selectedHeroDuringGame = null;
				hp =0;
				actions = 0;
				damage = 0;
				vaccineAmount = 0;
				supplyAmount = 0;
			}
			
		}
		
	}
	
	
	public static void enableButtons(Pane buttons,Pane buttons2) {
		for (Node node : buttons.getChildren()) {
			node.setDisable(false);
		}
		for(Node node : buttons2.getChildren()) {
			node.setDisable(false);
		}
	}
	
	public static void updateHeroes(HBox hbox, GridPane map,Pane buttons,Pane buttons2,GridPane heroInfo,VBox vboxright,GridPane targetInfo) {
			hbox.getChildren().clear();
			
		for (int i=0;i<Game.heroes.size();i++) {
			
			final int finali=i;
			Button hero = new Button(Game.heroes.get(i).getName());
			if(selectedHeroDuringGame!=null) {
				if((selectedHeroDuringGame.getName().equals(hero.getText()))){
					hero.setDisable(true);
				}
				else {
					hero.setDisable(false);
				}
			}
			else {
				hero.setDisable(false);
			}
			
			
			hero.setMinHeight(50);
			hero.setMinWidth(50);
			hero.setOnAction(e->{
				
				vboxright.getChildren().remove(heroInfo);
				selectedHeroDuringGame = Game.heroes.get(finali);
				type = Game.heroes.get(finali).getClass().getName().substring(17);
				damage = Game.heroes.get(finali).getAttackDmg();
				actions = Game.heroes.get(finali).getActionsAvailable();
				hp = Game.heroes.get(finali).getCurrentHp();
				name = Game.heroes.get(finali).getName();
				vaccineAmount = Game.heroes.get(finali).getVaccineInventory().size();
				supplyAmount = Game.heroes.get(finali).getSupplyInventory().size();
				updateHeroBorder(selectedHeroDuringGame,map);
				enableButtons(buttons,buttons2);
				updateHeroInfo(heroInfo);
				vboxright.getChildren().add(heroInfo);
				
				if(target!=null) {
					targetHp = target.getCurrentHp();
					targetName = target.getName();
					Label targname = new Label("Name: "+targetName+"\n");
					Label targHp = new Label("HP: "+targetHp+"\n");
					targetInfo.getChildren().clear();
					targetInfo.setRowIndex(targname, 0);
					targetInfo.setRowIndex(targHp, 1);
					targetInfo.getChildren().addAll(targname,targHp);
					vboxright.getChildren().remove(targetInfo);
					vboxright.getChildren().add(targetInfo);
				}
				vboxright.setMargin(heroInfo, new Insets(15, 12, 15, 12));
				vboxright.setMargin(targetInfo, new Insets(15, 12, 15, 12));
				hero.setDisable(true);
				for(int j=0;j<hbox.getChildren().size();j++) {
					Button node = (Button) hbox.getChildren().get(j);
					if(node.getText().equals(selectedHeroDuringGame.getName()))
						node.setDisable(true);
					else
						node.setDisable(false);
				}
			});
			hbox.getChildren().add(hero);
			
		}
		
	}
	
	public static void updateHeroInfo(GridPane heroInfo) {
		heroInfo.getChildren().clear();
		Label heroName = new Label("Name: "+name+"\n");
		Label heroType = new Label("Type: "+type+"\n");
		Label heroHp = new Label("HP: "+selectedHeroDuringGame.getCurrentHp()+"\n");
		Label heroDamage = new Label("Damage: "+selectedHeroDuringGame.getAttackDmg()+"\n");
		Label heroActions = new Label("Actions Left: "+selectedHeroDuringGame.getActionsAvailable());
		Label heroVaccines = new Label("Vaccines: "+selectedHeroDuringGame.getVaccineInventory().size());
		Label heroSupplies = new Label("Supplies: "+selectedHeroDuringGame.getSupplyInventory().size());
		heroInfo.setRowIndex(heroName, 0);
		heroInfo.setRowIndex(heroType, 1);
		heroInfo.setRowIndex(heroHp, 2);
		heroInfo.setRowIndex(heroDamage, 3);
		heroInfo.setRowIndex(heroActions, 4);
		heroInfo.setRowIndex(heroVaccines,5);
		heroInfo.setRowIndex(heroSupplies,6);
		heroInfo.getChildren().addAll(heroName,heroType,heroHp,heroDamage,heroActions,heroVaccines,heroSupplies);
	}
	
	public static void updateHeroBorder(Hero hero,GridPane map) {
		StackPane cellView = new StackPane();
		cellView.setMinHeight(45);
		cellView.setMinWidth(45);
		cellView.setStyle("-fx-border-color: red");
		map.setRowIndex(cellView, 14-hero.getLocation().x);
		map.setColumnIndex(cellView, hero.getLocation().y);
		map.getChildren().add(cellView);
//		update red to black
//		map.getChildren().remove(map.getChildren().size()-1);
	}
	
	public static void scene3(BorderPane sc2,Stage primaryStage,boolean gameWon) {
		BorderPane root = sc2;
		root.getChildren().clear();
		Label win = new Label("Game WON!");
		Label lose = new Label("Game LOST!");
		win.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
		lose.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
		if(gameWon) {
			root.setCenter(win);
		}
		else {
			root.setCenter(lose);
		}
		Scene scene3 = new Scene(root,1400,800);
		primaryStage.setScene(scene3);
	}
	
	
	public static void main(String[]args) {
		launch(args);
	}
	
	
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    return hbox;
	}
	public VBox addVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(15, 12, 15, 12));
	    vbox.setSpacing(10);
	    return vbox;
	}
}

