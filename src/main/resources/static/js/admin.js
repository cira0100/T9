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
    if(kategorija==="Svi")
    {
        kategorija="";
    }
    if(podkategorija==="Svi")
    {
        podkategorija="";
    }
    window.location="/adminIndexTrazi?pretraga="+pretraga+"&kategorija="+kategorija+"&podkategorija="+podkategorija;
}

function ListaKorisnika(){
    window.location="/listaKorisnika?token="+document.cookie;
}


function registruj(){

            var username=document.getElementById("Username").value;
                var password=document.getElementById("InputPassword").value;
                var email=document.getElementById("Email").value;
                var ime=document.getElementById("Ime").value;
                var prezime=document.getElementById("Prezime").value;

                var alertHolder=document.getElementById("alert_placeholder");
                var uspeh='<div class="alert alert-dismissible alert-success">'
                                                                               +'<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                                                               +'<strong>Uspesno ste se registrovali!</strong> '
                                                                               +'</div>';

                var poruka=TestAdmin(username,password,email,ime,prezime);
                    if(poruka===""){
                    fetch("/registrujAdmina?username="+username+"&password="+password+"&mail="+email+"&ime="+ime+"&prezime="+prezime+"&token="+document.cookie)
                        .then(function(response){
                        response.text().then(function(text){
                            if(text=="false"){
                            var poruka="Korisnik vec postoji";
                                alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                                + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                +  '<strong>Greska!!!</strong>'+poruka
                                +' </div>';
                    }
                            else
                            {
                                alertHolder.innerHTML=uspeh;
                            }


                        });


                        })
                    }else{
                        alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                               + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                              +  '<strong>Greska!!!</strong>'+poruka
                             +' </div>';
                    }




    }

    function ValidateEmail(email) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    function TestAdmin(username,password,email,ime,prezime){
                var message="";
                if(username===""){
                    message="Username ne sme biti prazan";
                }else if(password==="")
                {
                    message="Password ne sme biti prazan";
                }else if(!ValidateEmail(email))
                {
                    message="Email mora biti validan";
                }else if(ime==="")
                {
                    message="Ime ne sme biti prazana";
                }else if(prezime==="")
                {
                    message="Prezime ne sme biti prazana";
                }

                return message;
    }

    function logOut(){
    document.cookie="-1";
    window.location="/";
    }