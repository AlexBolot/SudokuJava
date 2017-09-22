<h1>SudokuJava <a href="https://www.codefactor.io/repository/github/alexbolot/sudokujava"><img src="https://www.codefactor.io/repository/github/alexbolot/sudokujava/badge" alt="" /></a></h1>
<h2>Summary :</h2>
<ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#1---about-the-project ">About the project</a><br />
<ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#11---the-goal ">The goal</a></li>
</ol>
</li>
<li><a href="https://github.com/AlexBolot/SudokuJava#2---package-tree-organisation ">Package tree organisation</a>
<ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#21---controller ">Controller</a></li>
<li><a href="https://github.com/AlexBolot/SudokuJava#22---model ">Model</a></li>
<ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#221---case ">Case</a></li>
<li><a href="https://github.com/AlexBolot/SudokuJava#222---grille ">Grille</a></li>
</ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#23---service ">Service</a>
<ol>
<li><a href="https://github.com/AlexBolot/SudokuJava#231---app ">App</a></li>
</ol>
</li>
</ol>
</li>
<li><a href="https://github.com/AlexBolot/SudokuJava/#3---coming-soon ">Coming soon</a></li>
<li><a href="https://github.com/AlexBolot/SudokuJava/#4---libraries ">Libraries</a></li>
<li><a href="https://github.com/AlexBolot/SudokuJava/#5---author ">Author</a></li>
</ol>
<h2>1 - About the project</h2>
<h3>1.1 - The goal</h3>
<p>This project allows you to Generate, Save, Load and Solve some Sudoku Grids.</p>
<h2>2 - Package tree organisation</h2>
<h3>2.1 - Controller</h3>
<p>In this package, as the name says it, you will find the java classes working as controller of the JavaFX views (.fxml). There is not much more to say about this package.</p>
<h3>2.2 - Model</h3>
<p>This package is where the objects and their managers are located.<br />The Class names are quite self-explanatory in my opinion, except for the ChallengeManager and the PlayerManager.<br />Their purpose is to handle a list of challenges/players and allow you to manipulate them more easily.<br />For example in the PlayerManager :</p>
<pre>//... Other lines of code ...//<br /><br />public static Player get (String name) {...}<br /><br />public static Player getFirst () {...}<br /><br />public static Player getPrevious (Player player) {...}<br /><br />public static Player getNext (Player player) {...}<br /><br />public static Player getRandom () {...}<br /><br />//... Other lines of code ...//</pre>
<h3>2.3 - Service</h3>
<h4>2.3.1 - App</h4>
<p>This class starts the JavaFX Application and calls the first GUI (ChallengeTypesView). It also creates the Players (for now, it will change in the next update).</p>
<h4>2.3.2 - ChallengeCreator</h4>
<p>This class creates all the challenges and adds them to the ChallengeManager if they match the <a href="https://github.com/AlexBolot/SudokuJava#13---selecting-types">list of selected challenge types</a>.<br />Note : this class will be removed in the next update.</p>
<h4>2.3.3 - Const</h4>
<p>This class is composed only of public static fields.<br />The goal is to centralise most variables, to be able to change them only from here. It reduces typo mistakes and allows quick modifiaction (view size, view path, etc).</p>
<h2>3 - Coming soon</h2>
<p>Please check the TODO.md file in this repository to find out what updates are planned.</p>
<h2>4 - Libraries</h2>
<p>Only 3 libraries are used for this Maven QuickStart project</p>
<ul>
<li>Java8 : JDK 1.8.111</li>
<li>Maven : JUnit 4.12</li>
<li>Maven : hamcrest-core:1.3</li>
</ul>
<p>Note : those libraries are already included in : org.jetbrains.idea.maven.model.MavenArchetype@88f75e0f</p>
<h2>5 - Author</h2>
<p>Alexandre BOLOT<br />My <a href="https://github.com/AlexBolot">GitHub</a><br />My <a href="https://www.linkedin.com/in/alexandrebolot">LinkedIn</a></p>
