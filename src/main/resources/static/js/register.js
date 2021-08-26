function promeniFormu(Id){
    if(Id==1)
    {
        document.getElementById("RadnikForma").style.display = "none";
        document.getElementById("PoslodavacForma").style.display = "block";
        document.getElementById("DugmeZaRegistraciju").setAttribute("onClick","javascript: Registruj(1);");
    }else if(Id==2)
    {
        document.getElementById("PoslodavacForma").style.display = "none";
        document.getElementById("RadnikForma").style.display = "block";
        document.getElementById("DugmeZaRegistraciju").setAttribute("onClick","javascript: Registruj(2);");
    }
}

function login(){
    var username=document.getElementById("Username").value;
    var password=document.getElementById("InputPassword").value;
    var alertHolder=document.getElementById("alert_placeholder");
    var poruka=loginValidation(username,password);
    if(poruka===""){
    fetch("/login?username="+username+"&password="+password)
    .then(function(response){
    response.text().then(function(text){
        if(text==""){
        alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                                    + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                    +  '<strong>Greska!!!</strong>Pogresan Login'
                                    '</div>';
        }else
        {
        document.cookie=text;
        preusmeriLogin(text);
        }


    });


    })}
    else
    {
    alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                            + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                            +  '<strong>Greska!!!</strong>'+poruka
                            +' </div>';
    }

}
function preusmeriLogin(token){
    fetch("/tokenTotype?token="+token)
    .then(function(response){
    response.text().then(function(tip){
        if(tip==-1){
        alertHolder.innerHTML='<div class="alert alert-dismissible alert-danger">'
                                    + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                    +  '<strong>Greska!!!</strong>Pogresan Login'
                                    '</div>';
        }else
        {
            if(tip==1)
            {
                document.location.href = '/radnikIndex';
                
            }else if(tip==2){
                document.location.href = '/poslodavacIndex';
                
            }else if(tip==3){
                document.location.href = '/adminIndex';
                
            }
        
        }


    });


    })
}

function loginValidation(username,password){
    poruka=""
    if(username==="")
    {
       poruka="Username ne sme biti prazan";
    }else if(password===""){
       poruka="Password ne sme biti praza";
    }
    return poruka;
}

function Registruj(Id){
    if(Id==1){
        var username=document.getElementById("Username1").value;
        var password=document.getElementById("InputPassword1").value;
        var email=document.getElementById("InputEmail1").value;
        var naziv=document.getElementById("InputNaziv").value;
        var adresa=document.getElementById("InputAdresa").value;
        var slika=username+"."+document.getElementById("InputSlika1").value.split(".").pop();
        var alertHolder=document.getElementById("alert_placeholder");
        var uspeh='<div class="alert alert-dismissible alert-success">'
                                                                       +'<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                                                       +'<strong>Uspesno ste se registrovali!</strong> '
                                                                       +'</div>';      
        var slika1=document.getElementById("InputSlika1").value;
         var poruka=testPoslodavac(username,password,email,naziv,adresa,slika1);
            if(poruka===""){
            fetch("/registrujPoslodavca?username="+username+"&password="+password+"&mail="+email+"&naziv="+naziv+"&adresa="
            +adresa+"&slika="+slika)
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
                        uploadfile(Id,username);
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
            
            
            
            
    }else if(Id==2)
    {
        var username=document.getElementById("Username").value;
        var password=document.getElementById("InputPassword").value;
        var email=document.getElementById("InputEmail").value;
        var ime=document.getElementById("InputIme").value;
        var prezime=document.getElementById("InputPrezime").value;
        var date=encodeURI(document.getElementById("date").value);
        var slika=username+"."+document.getElementById("InputSlika").value.split(".").pop();
        
        var alertHolder=document.getElementById("alert_placeholder");
        var uspeh='<div class="alert alert-dismissible alert-success">'
                                                                       +'<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
                                                                       +'<strong>Uspesno ste se registrovali!</strong> '
                                                                       +'</div>';
        var slika1=document.getElementById("InputSlika").value;
         var poruka=testRadnik(username,password,email,ime,prezime,date,slika1);
         
            if(poruka===""){
            fetch("/registrujRadnika?username="+username+"&password="+password+"&mail="+email+"&ime="+ime+"&prezime="+prezime+"&date="+date
        +"&slika="+slika)
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
                        uploadfile(Id,username);
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

}


function ValidateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function testPoslodavac(username,password,email,naziv,adresa,slika){
    
    var regex=new RegExp("^[a-zA-Z0-9]*$");

    var message="";
        var glavnaPoruka="";
        if(username==="" || !username.match(regex)){
            message="Username ne sme biti prazan i ne sme sadrzati specijalne karaktere";
        }else if(password==="")
        {
            message="Password ne sme biti prazan";
        }else if(!ValidateEmail(email))
        {
            message="Email mora biti validan";
        }else if(naziv===""){
            message="Naziv ne sme biti prazan";
            
        }else if(adresa==="")
        {
            message="Adresa ne sme biti prazana";
        }else if(slika==="")
        {
            message="Slika ne sme biti prazana";
        }
        else{
        var file=document.getElementById("InputSlika1").files[0];
        var  fileType = file['type'];
        var validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
        if (!validImageTypes.includes(fileType)) {
            message="Slika mora biti gif,jpeg ili png";
        }
    }
        if(message==="")
        message=proveraVelicineFajla(document.getElementById("InputSlika1").files[0].size);
        
        
        
        return message;
    
    
}

function testRadnik(username,password,email,ime,prezime,date,slika){
    var message="";
    var regex=new RegExp("^[a-zA-Z0-9]*$");
    var date=decodeURI(date);
        var glavnaPoruka="";
        if(username==="" || !username.match(regex)){
            message="Username ne sme biti prazan i ne sme sadrzati specijalne karaktere";
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
        }else if(!isValidDate(date))
        {
            message="Datum mora biti valid i u formatu yyyy-mm-dd";
        }
        else if(slika==="")
        {
            message="Slika ne sme biti prazana";
        }else{
        var file=document.getElementById("InputSlika").files[0];
        var  fileType = file['type'];
        var validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
        if (!validImageTypes.includes(fileType)) {
            message="Slika mora biti gif,jpeg ili png";
        }
    }
        if(message==="")
        message=proveraVelicineFajla(document.getElementById("InputSlika").files[0].size);
        
        return message;
    
}
function isValidDate(dateString) {
  var regEx = /^\d{4}-\d{2}-\d{2}$/;
  if(!dateString.match(regEx)) return false;  // Invalid format
  var d = new Date(dateString);
  var dNum = d.getTime();
  if(!dNum && dNum !== 0) return false; // NaN value, Invalid date
  return d.toISOString().slice(0,10) === dateString;
}


function uploadfile(Id,username){
    let formData = new FormData();
    var photo;
    var ext;
    if(Id===1){
        photo=document.getElementById("InputSlika1").files[0];
        ext=document.getElementById("InputSlika1").value.split(".").pop();
    }else if(Id===2)
    {
        photo=document.getElementById("InputSlika").files[0];
        ext=document.getElementById("InputSlika").value.split(".").pop();
        
        
    }else return;
    var blob =photo.slice(0, photo.size); 
    var newFile = new File([blob], username+"."+ext);

    formData.append("file", newFile);
    fetch('/upload', {method: "POST", body: formData});
}

function proveraVelicineFajla(velicina){
    
    var sizeInMB = (velicina / (1024*1024)).toFixed(2);
    if(sizeInMB>10)
        return "velicina mora biti manja od 10MB";
    return "";
}