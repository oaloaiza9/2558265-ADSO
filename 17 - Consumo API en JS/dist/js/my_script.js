let nombrePokemon = null;
let imgPokemon = null;
let contentButtons = null;
let btnBack = null;
let btnNext = null;

window.onload = function(){
	nombrePokemon = document.getElementById("nombrePokemon");
	imgPokemon = document.getElementById("imgPokemon");
	contentButtons = document.getElementById("contentButtons");
	btnBack = document.getElementById("btnBack");
	btnNext = document.getElementById("btnNext");

	pokemonsLoad( "https://pokeapi.co/api/v2/pokemon" );
}

function pokemonsLoad( endpoint ){
	console.log("Iniciando consumo API: "+endpoint);

	fetch( endpoint )
	.then( respuesta => respuesta.json() )
	.then( data => {

		contentButtons.innerHTML = "";
		for (var i=0; i<data.results.length; i++) {
			var temp = `<button class="col-5 col-lg-2 m-1 btn btn-outline-primary"> ${ data.results[i].name } </button>`;
			contentButtons.innerHTML += temp;
		}

		btnBack.setAttribute("onclick", ` pokemonsLoad("${ data.previous }") `);
		btnNext.setAttribute("onclick", ` pokemonsLoad("${ data.next }") `);
	});
}