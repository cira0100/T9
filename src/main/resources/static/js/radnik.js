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

function pretragaPoslodavaca(){

    var pretraga=document.getElementById("pretragaText").value;
    window.location="/listaPoslodavacaRadnikTrazi?pretraga="+pretraga;
}
function posetiOglas(i){
    var token=document.cookie;
    window.location="/oglasRadnik?idOglasa="+i+"&token="+token;
}

function PostKom(id){
    var TextArea=document.getElementById("Komentar").value;
    
    if(TextArea===""){
        alert("Komentar ne sme biti prazan");
    }else{
        
        fetch("/postKom?token="+document.cookie+"&idOgl="+id+"&tekst="+TextArea)
                    .then(function(response){
                    response.text().then(function(text){
                        if(text=="false"){
                        alert("Greska");
                }
                        else
                        {
                            window.location.reload();
                        }


                    });


                    })
        
    }
}

function Lajkuj(id){
    
    fetch("/lajkujOglas?token="+document.cookie+"&idOgl="+id)
                    .then(function(response){
                    response.text().then(function(text){
                        if(text=="false"){
                        alert("Greska");
                }
                        else
                        {
                            window.location.reload();
                        }


                    });


                    })
    
    
    
    
}
function proveraVelicineFajla(velicina){
    
    var sizeInMB = (velicina / (1024*1024)).toFixed(2);
    if(sizeInMB>10)
        return "velicina mora biti manja od 10MB";
    return "";
}

function GenImage(length){
    //edit the token allowed characters
    var a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".split("");
    var b = [];  
    for (var i=0; i<length; i++) {
        var j = (Math.random() * (a.length-1)).toFixed(0);
        b[i] = a[j];
    }
    return b.join("");
}

function uploadfile(name){
    let formData = new FormData();
    var photo;
    photo=document.getElementById("CV").files[0];

        
    var blob =photo.slice(0, photo.size); 
    var newFile = new File([blob], name);

    formData.append("file", newFile);
    fetch('/upload', {method: "POST", body: formData});
    
}

function PrijaviSe(id){
    var ext="."+document.getElementById("CV").value.split(".").pop();
    var file1=GenImage(16)+ext;
    var poruka=proveraVelicineFajla(document.getElementById("CV").files[0].size);
    if(ext!=".pdf")
        poruka="pogresan format";
    if(poruka===""){
        uploadfile(file1);
        fetch("/prijavaOglas?token="+document.cookie+"&idOgl="+id+"&cv="+file1)
                    .then(function(response){
                    response.text().then(function(text){
                        if(text=="false"){
                        alert("Greska ili ste se vec prijavili");
                }
                        else
                        {
                           alert("Uspesno ste se prijavili");
                        }


                    });


                    })
        
    }else
    {
        alert(poruka);
    }
    
}