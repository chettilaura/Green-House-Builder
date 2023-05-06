package it.polito.did.gruppo8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.Navigator
import it.polito.did.gruppo8.R
import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.ui.theme.GameSkeletonTheme
import it.polito.did.gruppo8.util.myComposable.*


@Composable
fun MainMenuScreen(/*navController: NavController,*//* onCreateNewGame:() -> Unit, onPreJoinGame:() -> Unit, navigator: Navigator,*/ vm:GameViewModel,modifier: Modifier = Modifier) {


    Box(modifier = Modifier.fillMaxSize(1f)) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            AppName()
        /*
        MyButton("NEW GAME", "Create house button", {navController.navigate(ScreenName.NewGame.route) })
        MyButton("JOIN GAME", "Create house button", { navController.navigate(ScreenName.JoinGame.route)})

         */
            MyButton("NEW GAME", "Create house button", {vm.onNewGameButtonPressed()})
            MyButton("JOIN GAME", "Create house button", { vm.onJoinGameButtonPressed() })


            Spacer(modifier = Modifier.size(30.dp))

//TUTORIAL POP UP

            var tutorialPopUpControl by remember { mutableStateOf(false) }
            MyButton(title = "TUTORIAL", description = "Tutorial", {tutorialPopUpControl = true})


            Spacer(modifier = Modifier.size(5.dp))

            if (tutorialPopUpControl) {

                Popup(
                    alignment = Center,
                    onDismissRequest = { tutorialPopUpControl = false }) {
                    popUpTutorial()
                }
            }


//SETTINGS POP UP

            var settingsPopUpControl by remember { mutableStateOf(false) }
            MyButton(title = "SETTINGS", description = "Settings", {settingsPopUpControl = true})


            Spacer(modifier = Modifier.size(5.dp))

            if (settingsPopUpControl) {

                Popup(
                    alignment = Center,
                    onDismissRequest = { settingsPopUpControl = false }) {
                    settingsPopUp()
                }
            }

        }

    }
}

//COMPOSABLES FUNCTIONS

/*
    TODO: Valutare un metodo per generalizzare anche il PopUp, se fattibile.
     -Mattia
 */
@Composable
fun popUpTutorial() {
    val shape = RoundedCornerShape(30.dp)
    Box(
        modifier = Modifier
            .size(400.dp, 400.dp)
            .clip(shape)
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.tut_background),
                contentDescription = "tutorial background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "GUIDE",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Instructions()
                }

            }
        }

    }
}

@Composable
fun settingsPopUp() {
    val shape = RoundedCornerShape(30.dp)
    Box(
        modifier = Modifier
            .size(400.dp, 400.dp)
            .clip(shape)
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = "Immagine di sfondo",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.tut_background),
                contentDescription = "tutorial background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "SETTINGS",
                            fontFamily = caveatBold,
                            color = Color.White,
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Column(modifier = Modifier
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Row(){
                            Text("MUSIC",
                                fontFamily = caveatBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h4)
                            SwitchDemo()

                        }
                        Row(){
                            Text("SFX",
                                fontFamily = caveatBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h4)
                            SwitchDemo()

                        }
                        Row(){
                            Text("VIBRATION",
                                fontFamily = caveatBold,
                                color = Color.Black,
                                style = MaterialTheme.typography.h4)
                            SwitchDemo()

                        }
                    }
                }

            }
        }

    }
}

@Composable
fun AppName(){
    Row(horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.app_name),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun Instructions(){
    Column(modifier = Modifier
        .padding(15.dp)
        .verticalScroll(rememberScrollState())) {
        //prendere da file scritto quando avremo il tutorial scritto
        Text(text = "Quel ramo del lago di Como, che volge a mezzogiorno, tra due catene non interrotte di monti, tutto a seni e a golfi, a seconda dello sporgere e del rientrare di quelli, vien, quasi a un tratto, a ristringersi, e a prender corso e figura di fiume, tra un promontorio a destra, e un’ampia costiera dall’altra parte; e il ponte, che ivi congiunge le due rive, par che renda ancor più sensibile all’occhio questa trasformazione, e segni il punto in cui il lago cessa, e l’Adda rincomincia, per ripigliar poi nome di lago dove le rive, allontanandosi di nuovo, lascian l’acqua distendersi e rallentarsi in nuovi golfi e in nuovi seni. La costiera, formata dal deposito di tre grossi torrenti, scende appoggiata a due monti contigui, l’uno detto di san Martino, l’altro, con voce lombarda, il Resegone, dai molti suoi cocuzzoli in fila, che in vero lo fanno somigliare a una sega: talchè non è chi, al primo vederlo, purchè sia di fronte, come per esempio di su le mura di Milano che guardano a settentrione, non lo discerna tosto, a un tal contrassegno, in quella lunga e vasta giogaia, dagli altri monti di nome più oscuro e di forma più comune. Per un buon pezzo, la costa sale con un pendìo lento e continuo; poi si rompe in poggi e in valloncelli, in erte e in ispianate, secondo l’ossatura de’ due monti, e il lavoro dell’acque. Il lembo estremo, tagliato dalle foci de’ torrenti, è quasi tutto ghiaia e ciottoloni; il resto, campi e vigne, sparse di terre, di ville, di casali; in qualche parte boschi, che si prolungano su per la montagna. Lecco, la principale di quelle terre, e che dà nome al territorio, giace poco discosto dal ponte, alla riva del lago, anzi viene in parte a trovarsi nel lago stesso, quando questo ingrossa: un gran borgo al giorno d’oggi, e che s’incammina a diventar città. Ai tempi in cui accaddero i fatti che prendiamo a raccontare, quel borgo, già considerabile, era anche un castello, e aveva perciò l’onore d’alloggiare un comandante, e il vantaggio di possedere una stabile guarnigione di soldati spagnoli, che insegnavan la modestia alle fanciulle e alle donne del paese, accarezzavan di tempo in tempo le spalle a qualche marito, a qualche padre; e, sul finir dell’estate, non mancavan mai di spandersi nelle vigne, per diradar l’uve, e alleggerire a’ contadini le fatiche della vendemmia. Dall’una all’altra di quelle terre, dall’alture alla riva, da un poggio all’altro, correvano, e corrono tuttavia, strade e stradette, più o men ripide, o piane; ogni tanto affondate, sepolte tra due muri, donde, alzando lo sguardo, non iscoprite che un pezzo di cielo e qualche vetta di monte; ogni tanto elevate su terrapieni aperti: e da qui la vista spazia per prospetti più o meno estesi, ma ricchi sempre e sempre qualcosa nuovi, secondo che i diversi punti piglian più o meno della vasta scena circostante, e secondo che questa o quella parte campeggia o si scorcia, spunta o sparisce a vicenda. Dove un pezzo, dove un altro, dove una lunga distesa di quel vasto e variato specchio dell’acqua; di qua lago, chiuso all’estremità o piuttosto smarrito in un gruppo, in un andirivieni di montagne, e di mano in mano più allargato tra altri monti che si spiegano, a uno a uno, allo sguardo, e che l’acqua riflette capovolti, co’ paesetti posti sulle rive; di là braccio di fiume, poi lago, poi fiume ancora, che va a perdersi in lucido serpeggiamento pur tra’ monti che l’accompagnano, degradando via via, e perdendosi quasi anch’essi nell’orizzonte. Il luogo stesso da dove contemplate que’ vari spettacoli, vi fa spettacolo da ogni parte: il monte di cui passeggiate le falde, vi svolge, al di sopra, d’intorno, le sue cime e le balze, distinte, rilevate, mutabili quasi a ogni passo, aprendosi e contornandosi in gioghi ciò che v’era sembrato prima un sol giogo, e comparendo in vetta ciò che poco innanzi vi si rappresentava sulla costa: e l’ameno, il domestico di quelle falde tempera gradevolmente il selvaggio, e orna vie più il magnifico dell’altre vedute.\n")
    }
} //aggiungere tutorial con eventuali immagini
@Composable
fun SwitchDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Switch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}  //da assegnare (music, sfx, vibration)

/*--------------------------------------------------------------------*/
//PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOpenScreen() {
    //val navigator = Navigator()
    val vm = GameViewModel()
    GameSkeletonTheme {
        //MainMenuScreen(navController = rememberNavController())
        MainMenuScreen(vm)
    }
}

