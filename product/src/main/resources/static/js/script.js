
const formulario = document.querySelector("form");
const Iname = document.querySelector(".name");
const Idescription= document.querySelector(".description");
const Ivalue = document.querySelector(".value");
const Icor = document.querySelector(".cor");
//funcão de cadastro

function cadastrar(){
fetch("http://localhost:8080/products",{
     headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
     },
     method:"POST",
     body:JSON.stringify({
                        name: Iname.value,
                         description: Idescription.value,
                         cor: Icor.value,
                        value: Ivalue.value
                        })
})
.then(function(res){console.log(res)})
.catch(function(res){console.log(res)})
};

//limpar
function limpar(){
    Iname.value ="";
    Idescription.value="";
    Icor.value ="";
    Ivalue.value ="";
};

// Evento que submete a ação
formulario.addEventListener('submit',function(event){
 event.preventDefault();
  cadastrar();
  limpar();
 });

