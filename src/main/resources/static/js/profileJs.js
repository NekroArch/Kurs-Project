
$('#addAttribute').change(function () {
  if (this.checked != true) {
    $("#temp").hide();
  } else {
    $("#temp").show();
  }
});

let collectionId = 0;

$(function () {
  $('button[id="collectionNameBtn"]').click(function () {
    let table = document.getElementById('table');
    let attributeContext;
    let attributeContexts = this.getElementsByClassName('attributeContext');
    let attributeName = [];
    let attributeType = [];

    for (let i = 0; i < table.rows.length; i++) {
      let row = table.rows[i];
      if (row.cells[1].getElementsByTagName('button')[0].innerText == this.innerText) {
        collectionId = row.cells[6].getElementsByTagName('div')[0].innerText;
        attributeContext = row.cells[1].getElementsByClassName('attributeContext');
      }
    }
    for (let i = 0; i < attributeContext.length; i++) {
      attributeName[i] = attributeContext[i].children[0].innerText;
      attributeType[i] = attributeContext[i].children[1].innerText.toLowerCase();
    }

    let er
    let paru

    er = document.getElementById("itemModalContext1");


    paru = document.createElement("div");
    paru.className = "form-group";
    paru.innerHTML =
        "<label for='recipient-name' class='col-form-label'>" + "Name:" + "</label>" +
        "<input type='text' class='form-control' id='recipient-name' placeholder='Name''>";
    er.appendChild(paru);

    paru = document.createElement("div");
    paru.className = "form-group";
    paru.innerHTML =
        "<label for='recipient-name' class='col-form-label'>" + "Tags:" + "</label>" +
        "<input type='text' class='form-control' id='tagName' placeholder='Tags'>";
    er.appendChild(paru);

    for (let i =0; i < attributeContext.length; i++){

      paru = document.createElement("div");
      paru.className = "form-group";
      paru.innerHTML =
          "<label for='recipient-name' class='col-form-label attibuteCreateName'>" + attributeName[i] + "</label>" +
          "<input type='text' class='form-control attributeCreateValue'>";

      er = document.getElementById("itemModalContext1");
      er.appendChild(paru);


    }
    switchBtnColor();
  });
});

$(function () {
  $('button[id="itemNameBtn"]').click(function () {
    let tags = [].map.call(this.getElementsByClassName('tags'), function (obj) {
      return obj.innerText;
    });

    let ItemAttributeMerg = this.getElementsByClassName('ItemAttributeMerg');
    let attributeValue = [];
    let attributeName = [];

   
    for (let i = 0; i < ItemAttributeMerg.length; i++) {
      attributeValue[i] = ItemAttributeMerg[i].children[0].innerText;
      attributeName[i] = ItemAttributeMerg[i].children[1].innerText;
    }

    let er
    let paru

    er = document.getElementById("itemModalContext2");


    paru = document.createElement("div");
    paru.className = "form-group";
    paru.innerHTML =
        "<label for='recipient-name' class='col-form-label'>" + "Name: " +
        this.getElementsByClassName('itemName')[0].innerText + "</label>"
    er.appendChild(paru);

    paru = document.createElement("div");
    paru.className = "form-group";
    paru.innerHTML =
        "<label for='recipient-name' class='col-form-label'>" + "Tags: " + tags + "</label>"
    er.appendChild(paru);

    for (let i =0; i < attributeName.length; i++) {

      paru = document.createElement("div");
      paru.className = "form-group";
      paru.innerHTML =
          "<label for='recipient-name' class='col-form-label'>" + attributeName[i] + ": " + attributeValue[i] + "</label>"

      er = document.getElementById("itemModalContext2");
      er.appendChild(paru);
    };
  });
});

function checkedAll(flag) {
  let table = document.getElementById('table');

  if (flag == 0) {
    for (let i = 0; i < table.rows.length; i++) {
      let row = table.rows[i];
      row.cells[0].getElementsByTagName('input')[0].checked = true;
    }
  } else {
    for (let i = 0; i < table.rows.length; i++) {
      let row = table.rows[i];
      row.cells[0].getElementsByTagName('input')[0].checked = false;
    }
  }
};

function createCollection() {
  let modalFade = document.getElementById("collectionContext");
  let attributeContext = [];



  for (let i = 0; i < 3; i++) {
    if (modalFade.getElementsByClassName("attributeType")[i].value != 'Attribute' && modalFade.getElementsByClassName("attributeName")[i].value != '') {
      attributeContext.push({
        "attributeName": modalFade.getElementsByClassName("attributeName")[i].value
      });
    }
  }

  let context;

  if (location.pathname == "/profile/userCollection") {

    context = {
      "name": modalFade.getElementsByTagName("input")[0].value,
      "imageUrl": modalFade.getElementsByTagName("input")[1].value,
      "description": modalFade.getElementsByTagName("input")[2].value,
      "topic": modalFade.getElementsByTagName("input")[3].value,
      "userName": localStorage.getItem('user'),
      "attributeContext": attributeContext
    };

  } else {

    context = {
      "name": modalFade.getElementsByTagName("input")[0].value,
      "imageUrl": modalFade.getElementsByTagName("input")[1].value,
      "description": modalFade.getElementsByTagName("input")[2].value,
      "topic": modalFade.getElementsByTagName("input")[3].value,
      "userName": document.getElementById('userName').innerText,
      "attributeContext": attributeContext
    };

  }

  postCreateCollection(JSON.stringify(context));
}

function createItem() {
  let modalFade = document.getElementById("itemModalContext1");
  let itemName = [];

 let attibuteCreateName = modalFade.getElementsByClassName("attibuteCreateName");
 let attributeCreateValue = modalFade.getElementsByClassName("attributeCreateValue");


 let attibuteName = [];
 let attributeValue = [];


 for(let i = 0; i < attibuteCreateName.length; i++){
   if(attributeCreateValue[i].value != ""){
     attibuteName[i] = attibuteCreateName[i].innerText;
     attributeValue[i] = attributeCreateValue[i].value;
  }
 }

  itemName.push({
    "name": modalFade.getElementsByTagName("input")[0].value,
    "collectionId": collectionId,
    "tagName": modalFade.getElementsByTagName("input")[1].value,
    "itemAttributeNameDto": attibuteName,
    "itemAttributeValueDto": attributeValue
  });

  postCreateItem(JSON.stringify(itemName));
}

$("#itemModel1").on("hidden.bs.modal", function(){
  $("#itemModalContext1").empty();
});

$("#tagModel").on("hidden.bs.modal", function(){
  $("#itemModalContext2").empty();
});


function deleteCollection() {
  let table = document.getElementById("table");
  let id = [];
  for (let i = 0; i < table.rows.length; i++) {
    let row = table.rows[i];
    if (row.cells[0].getElementsByTagName('input')[0].checked) {
      id.push(row.cells[6].getElementsByTagName('div')[0].innerText);
    }
  }

  postDeleteCollection(JSON.stringify(id));
};

function postDeleteCollection(data) {

  fetch("/profile/deleteCollection",{
    method: "DELETE",
    body:data,
    headers:{"Content-Type": "application/json"},
  }).then( response => {
    if ($(".ar:not(:checked)").length === $(".ar").length || response.status === 405 || response.status === 500) {
      toastr.options.positionClass = "toast-bottom-right";
      toastr.error("Error");
      return false;
    } else if (response.status === 200) {
      toastr.options.positionClass = "toast-bottom-right";
      toastr.success("Collection deleted");
      update();
    }
  });
};

function postCreateCollection(data) {
  let modalFade = document.getElementById("collectionContext");

  fetch("/profile/createCollection",{
    method: "POST",
    body:data,
    headers:{"Content-Type": "application/json"},
  }).then( response => {
    if (modalFade.getElementsByTagName("input")[0].value === "" || modalFade.getElementsByTagName("input")[2].value === "" || 
        modalFade.getElementsByTagName("input")[3].value === ""){
        toastr.options.positionClass = "toast-bottom-right";
        toastr.warning("You must to fill in the form fields!");
      }else if (response.status === 405 || response.status === 500) {
      toastr.options.positionClass = "toast-bottom-right";
      toastr.error("Error");
    } else if (response.status === 200) {
      toastr.options.positionClass = "toast-bottom-right";
      toastr.success("Collection created");
      update();
    }
  });

};

function postCreateItem(data) {

  fetch("/profile/createItem",{
    method: "POST",
    body:data,
    headers:{"Content-Type": "application/json"},
  }).then( response => {
    if (response.status === 405 || response.status === 500){
      toastr.options.positionClass = "toast-bottom-right";
      toastr.error("Error");
      return false;
    } else if (response.status === 200) {
      toastr.options.positionClass = "toast-bottom-right";
      toastr.success("Item added");
      update();
    }
  });

};



function update() {
  setTimeout(function () {
    location.reload();
  }, 1000);
}  