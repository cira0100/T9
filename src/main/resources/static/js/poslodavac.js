function getPodKategorije(){
    var kategorija=document.getElementById("KategorijeSelect").value;
    var podkategorijemain=document.getElementById("PodKategorijeSelect");

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
        var string="";
        for(var i=0;i<podkategorije.length;i++){
            string+='<option>'+podkategorije[i]+'</option>';
        }
        podkategorijemain.innerHTML=string;



    });


    })



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
    window.location="/poslodavacIndexTrazi?pretraga="+pretraga+"&kategorija="+kategorija+"&podkategorija="+podkategorija;
}

function mojiOglasi(){
    window.location="/mojiOglasi?token="+document.cookie;
}
function logOut(){
    document.cookie="-1";
    window.location="/";
    }

function dodajOglas(){
            var naziv=document.getElementById("Naziv").value;
            var date=encodeURI(document.getElementById("date").value);
            var kategorija=document.getElementById("KategorijeSelect").value;
            var podkategorija=document.getElementById("PodKategorijeSelect").value;
            var plata=document.getElementById("Plata").value;
            var slika=document.getElementById("InputSlika");
            var opis=document.getElementById("TextOpis").value;
            var remote=document.getElementById("CheckRemote").checked;
            if(plata==="")
                plata=-1;


            var alertHolder=document.getElementById("alert_placeholder");
            var uspeh='<div class="alert alert-dismissible alert-success">'
                                                                           +'<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                                                           +'<strong>Uspesno ste prijavi oglas!</strong> '
                                                                           +'</div>';
            var slika1=GenImage(16)+"."+document.getElementById("InputSlika").value.split(".").pop();
            var poruka=testOglas(naziv,date,kategorija,podkategorija,plata,slika.value,opis,remote);
                if(poruka===""){
                fetch("/registrujOglas?naziv="+naziv+"&datum="+date+"&kategorija="+kategorija+"&podkategorija="+podkategorija+
                        "&plata="+plata+"&slika="+slika1+"&tekst="+opis+"&remote="+remote+"&token="+document.cookie)
                    .then(function(response){
                    response.text().then(function(text){
                        if(text=="false"){
                        var poruka="GRESKA";
                            alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                            + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                            +  '<strong>Greska!!!</strong>'+poruka
                            +' </div>';
                }
                        else
                        {
                            uploadfile(slika1);
                            alertHolder.innerHTML=uspeh;
                        }


                    });


                    })


}
else{
    alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                            + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                            +  '<strong>Greska!!!</strong>'+poruka
                            +' </div>';
}
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
    photo=document.getElementById("InputSlika").files[0];

        
    var blob =photo.slice(0, photo.size); 
    var newFile = new File([blob], name);

    formData.append("file", newFile);
    fetch('/upload', {method: "POST", body: formData});
    
}

function testOglas(naziv,date,kategorija,podkategorija,plata,slika,opis,remote){
    var poruka="";
    var date=decodeURI(date);
    if(naziv===""){
        poruka="Naziv ne sme biti prazan";
    }else if(!isValidDate(date))
    {
        poruka="Datum mora biti validan";
    }else if(kategorija===""){
        poruka="Kategorija ne sme biti prazna";
    }else if(podkategorija===""){
        poruka="PodKategorija ne sme biti prazna";
    }else if(slika===""){
        poruka="Slika ne sme biti prazna";
    }else if(opis===""){
        poruka="opis ne sme biti prazan";
    }else {
        var file=document.getElementById("InputSlika").files[0];
        var  fileType = file['type'];
        var validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
        if (!validImageTypes.includes(fileType)) {
            poruka="Slika mora biti gif,jpeg ili png";
        }
    }
    if(poruka===""){
        poruka=proveraVelicineFajla(document.getElementById("InputSlika").files[0].size);
        }


        return poruka;
    
    
}

function isValidDate(dateString) {
  var regEx = /^\d{4}-\d{2}-\d{2}$/;
  if(!dateString.match(regEx)) return false;  // Invalid format
  var d = new Date(dateString);
  var dNum = d.getTime();
  if(!dNum && dNum !== 0) return false; // NaN value, Invalid date
  return d.toISOString().slice(0,10) === dateString;
}
function proveraVelicineFajla(velicina){
    
    var sizeInMB = (velicina / (1024*1024)).toFixed(2);
    if(sizeInMB>10)
        return "velicina mora biti manja od 10MB";
    return "";
}
function posetiOglas(i){
    var token=document.cookie;
    window.location="/oglasPoslodavac?idOglasa="+i+"&token="+token;
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

function ObrisiOglas(id){
    
    fetch("/obrisiOglas?token="+document.cookie+"&idOgl="+id)
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

