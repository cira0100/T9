function getPodKategorije(){
    var kategorija=document.getElementById("KategorijeSelect").value;
    var podkategorijemain=document.getElementById("PodKategorijeSelect");
    if(kategorija=="Svi")
    {
        podkategorijemain.innerHTML='<option>Svi</option>';
    }else{
    fetch("/getPodKategorije?kategorija="+kategorija)
    .then(function(response){
    response.text().then(function(podkategorije){
        podkategorije=podkategorije.split('"');
        for(var i=0;i<podkategorije.length;i++){
            if(podkategorije[i]===',' || podkategorije[i]==='[' || podkategorije[i]===']')
            {
                podkategorije.splice(i, 1);
            }
        }
        var string='<option>Svi</option>'
        for(var i=0;i<podkategorije.length;i++){
            string+='<option>'+podkategorije[i]+'</option>';
        }
        podkategorijemain.innerHTML=string;
        


    });


    })
}
    

}


function pretraga(){
    var pretraga=document.getElementById("pretragaText").value;
    var kategorija=document.getElementById("KategorijeSelect").value;
    var podkategorija=document.getElementById("PodKategorijeSelect").value;
    if(kategorija==="Svi")
    {
        kategorija="";
    }
    if(podkategorija==="Svi")
    {
        podkategorija="";
    }
    window.location="/radnikIndexTrazi?pretraga="+pretraga+"&kategorija="+kategorija+"&podkategorija="+podkategorija;
}

function logOut(){
    document.cookie="-1";
    window.location="/";
    }

function mojePrijave(){
        window.location="/mojePrijave?token="+document.cookie;
    }