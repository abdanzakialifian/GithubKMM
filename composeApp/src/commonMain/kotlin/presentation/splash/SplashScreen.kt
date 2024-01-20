package presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.coroutines.delay

private const val lottieData = """
{"v":"5.5.7","meta":{"g":"LottieFiles AE 0.1.21","a":"","k":"","d":"","tc":""},"fr":30,"ip":0,"op":80,"w":500,"h":500,"nm":"Github","ddd":0,"assets":[],"layers":[{"ddd":0,"ind":1,"ty":4,"nm":"logo","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":8,"s":[0]},{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":28,"s":[-8]},{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":48,"s":[8]},{"t":68,"s":[0]}],"ix":10},"p":{"a":0,"k":[250,250.416,0],"ix":2},"a":{"a":0,"k":[110.25,106.084,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":4,"s":[100,100,100]},{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":24,"s":[85,85,100]},{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":44,"s":[115,115,100]},{"t":64,"s":[100,100,100]}],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[60.747,0],[0,-59.694],[-43.698,-14.281],[0,2.869],[0.046,9.011],[0,0],[0,0],[0,0],[0,0],[-6.288,2.76],[-3.144,2.704],[0,41.414],[-7.059,7.563],[-5.914,14.886],[-21.039,-13.98],[-9.351,0.055],[-8.8,-2.403],[0,0],[1.101,-2.731],[0,-11.798],[24.475,-2.658],[0,-10.129],[0,-3.511],[-5.637,1.081],[0,47.694]],"o":[[-60.775,0],[0,47.767],[5.5,1.018],[0,-2.566],[-30.599,6.518],[-5.005,-12.475],[-9.965,-6.701],[11.046,0.752],[9.808,16.527],[0.99,-6.994],[-24.429,-2.704],[0,-11.798],[-1.238,-2.731],[0,0],[8.799,-2.403],[9.349,0.055],[20.899,-13.98],[5.912,14.886],[7.013,7.563],[0,41.524],[3.85,3.245],[0,14.466],[0,2.832],[44.01,-14.191],[0,-59.694]],"v":[[0,-105.834],[-110,2.26],[-34.787,104.816],[-27.271,99.618],[-27.408,81.24],[-64.46,66.738],[-76.697,50.926],[-75.928,44.362],[-59.079,55.499],[-27.042,64.492],[-20.075,50.036],[-70.18,-3.378],[-58.859,-32.381],[-57.896,-60.99],[-27.646,-49.907],[-0.146,-53.557],[27.354,-49.907],[57.466,-60.99],[58.565,-32.381],[69.841,-3.378],[19.653,49.945],[27.078,69.946],[26.94,99.546],[34.503,104.68],[110,2.26]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0,0,0,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[110.25,106.084],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":80,"st":0,"bm":0},{"ddd":0,"ind":2,"ty":4,"nm":"BG","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":4,"s":[0]},{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":24,"s":[-8]},{"i":{"x":[0.5],"y":[1]},"o":{"x":[0.5],"y":[0]},"t":44,"s":[8]},{"t":64,"s":[0]}],"ix":10},"p":{"a":0,"k":[250,250,0],"ix":2},"a":{"a":0,"k":[150.25,150.25,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":0,"s":[100,100,100]},{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":20,"s":[85,85,100]},{"i":{"x":[0.5,0.5,0.667],"y":[1,1,1]},"o":{"x":[0.5,0.5,0.333],"y":[0,0,0]},"t":40,"s":[115,115,100]},{"t":60,"s":[100,100,100]}],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[27.614,0],[0,0],[0,27.614],[0,0],[-27.614,0],[0,0],[0,-27.614],[0,0]],"o":[[0,0],[-27.614,0],[0,0],[0,-27.614],[0,0],[27.614,0],[0,0],[0,27.614]],"v":[[100,150],[-100,150],[-150,100],[-150,-100],[-100,-150],[100,-150],[150,-100],[150,100]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"tr","p":{"a":0,"k":[150.25,150.25],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"st","c":{"a":0,"k":[0,0,0,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":5,"ix":5},"lc":1,"lj":1,"ml":4,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false}],"ip":0,"op":80,"st":0,"bm":0}],"markers":[]}
"""

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000L)
        onNavigate()
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(lottieData))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        restartOnPlay = false
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = composition,
            progress = {
                progress
            }
        )
    }
}
