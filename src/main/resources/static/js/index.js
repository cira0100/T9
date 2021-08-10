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
        podkategorije=podkategorije.split('"')
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
    var remote=document.getElementById("CheckRemote").checked;
    if(kategorija==="Svi")
    {
        kategorija="";
    }
    if(podkategorija==="Svi")
    {
        podkategorija="";
    }
    window.location="/trazi?pretraga="+pretraga+"&kategorija="+kategorija+"&podkategorija="+podkategorija+"&remote="+remote;
}

function pretragaPoslodavaca(){
    var pretraga=document.getElementById("pretragaText").value;
    window.location="/listaPoslodavacaTrazi?pretraga="+pretraga;
}
function posetiOglas(i){
    var token=document.cookie;
    window.location="/oglas?idOglasa="+i;
}


function redirect(){

        fetch("/vratiTip?token="+document.cookie)
                            .then(function(response){
                            response.text().then(function(text){
                                if(text==1)
                                    {
                                        document.location.href = '/radnikIndex';

                                    }else if(text==2){
                                        document.location.href = '/poslodavacIndex';

                                    }else if(text==3){
                                        document.location.href = '/adminIndex';

                                    }
                                


                            });


                            })

}
