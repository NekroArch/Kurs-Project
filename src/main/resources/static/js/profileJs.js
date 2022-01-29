$('#itemModel').on('hidden.bs.modal', function () {
  $(this).find('form').trigger('reset');
  let type = ['text', 'date', 'number', 'comment'];
  for(let i = 0; i < 3; i++){
    document.getElementById(type[i]).hidden = true;
    document.getElementById(type[i] + 'Name').hidden = true;
    document.getElementById(type[i] + 'Name').innerText = '';
  }
});

$('#tagModel').on('hidden.bs.modal', function () {
  $(this).find('form').trigger('reset');
  let type = ['text', 'date', 'number', 'comment'];
  for(let i = 0; i < 3; i++){
    document.getElementById(type[i]).hidden = true;
    document.getElementById(type[i] + 'Name').hidden = true;
    document.getElementById(type[i] + 'Name').innerText = '';
  }
});

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
        collectionId = row.cells[5].getElementsByTagName('div')[0].innerText;
        attributeContext = row.cells[1].getElementsByClassName('attributeContext');
      }
    }
    for (let i = 0; i < attributeContext.length; i++) {
      attributeName[i] = attributeContext[i].children[0].innerText;
      attributeType[i] = attributeContext[i].children[1].innerText.toLowerCase();
    }

    let type = ['text', 'date', 'number', 'comment'];

    for (let i = 0; i < attributeType.length; i++) {
      for (let j = 0; j < type.length; j++) {
        if (attributeType[i] == type[j]) {
          document.getElementById(attributeType[i]).hidden = false;
          document.getElementById(attributeType[i] + 'Name').hidden = false;
          document.getElementById(attributeType[i] + 'Name').innerText = attributeName[j];
        }
      }
    }
  });
});

$(function () {
  $('button[id="itemNameBtn"]').click(function () {
    let tags = [].map.call(this.getElementsByClassName('tags'), function (obj) {
      return obj.innerText;
    });

    let attributeContext = this.getElementsByClassName('attributeContext');
    let attributeName = [];
    let attributeType = [];

    tags = tags.join(', ');
    document.querySelector('#tagModel [name="itemName"]').innerText = this.getElementsByClassName('itemName')[0].innerText;
    document.querySelector('#tagModel [name="tags"]').innerText = tags;
   
    for (let i = 0; i < attributeContext.length; i++) {
      attributeName[i] = attributeContext[i].children[0].innerText;
      attributeType[i] = attributeContext[i].children[1].innerText.toLowerCase();
    }

    let type = ['text', 'date', 'number', 'comment'];

    for (let i = 0; i < attributeType.length; i++) {
      for (let j = 0; j < type.length; j++) {
        if (attributeType[i] == type[j]) {
          document.getElementById(attributeType[i] + 'Name').hidden = false;
          document.getElementById(attributeType[i] + 'Name').innerText = attributeName[j];
        }
      }
    }
    
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
        "attributeName": modalFade.getElementsByClassName("attributeName")[i].value,
        "attributeType": modalFade.getElementsByClassName("attributeType")[i].value
      });
    }
  }

  let context;

  if (location.pathname == "/profile/userCollection") {

    context = {
      "name": modalFade.getElementsByTagName("input")[0].value,
      "description": modalFade.getElementsByTagName("input")[1].value,
      "topic": modalFade.getElementsByTagName("input")[2].value,
      "userName": localStorage.getItem('user'),
      "attributeContext": attributeContext
    };

  } else {

    context = {
      "name": modalFade.getElementsByTagName("input")[0].value,
      "description": modalFade.getElementsByTagName("input")[1].value,
      "topic": modalFade.getElementsByTagName("input")[2].value,
      "userName": document.getElementById('userName').innerText,
      "attributeContext": attributeContext
    };

  }

  postCreateCollection(JSON.stringify(context));
}

function createItem() {
  let modalFade = document.getElementById("itemModalContext");
  let itemName = [];

  itemName.push({
    "name": modalFade.getElementsByTagName("input")[0].value,
    "collectionId": collectionId,
    "tagName": modalFade.getElementsByTagName("input")[1].value
  });

  postCreateAttribute(JSON.stringify(itemName));
}


function deleteCollection() {
  let table = document.getElementById("table");
  let id = [];
  for (let i = 0; i < table.rows.length; i++) {
    let row = table.rows[i];
    if (row.cells[0].getElementsByTagName('input')[0].checked) {
      id.push(row.cells[5].getElementsByTagName('div')[0].innerText);
    }
  }

  postDeleteCollection(JSON.stringify(id));
};

function postDeleteCollection(data) {
  let xhr = new XMLHttpRequest();
  let url = "/profile/delete"

  xhr.open("DELETE", url, true);

  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send(data);
  update();
};

function postCreateCollection(data) {
  let xhr = new XMLHttpRequest();
  let url = "/profile/createCollection"

  xhr.open("POST", url, true);

  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send(data);
  update();
};

function getUser() {
  let xhr = new XMLHttpRequest();
  let url = "/profile/userCollection"

  xhr.open("Get", url, true);

  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send(data);
  update();
};

function postCreateAttribute(data) {
  let xhr = new XMLHttpRequest();
  let url = "/profile/createItem"

  xhr.open("POST", url, true);

  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send(data);
  update();
};



function update() {
  setTimeout(function () {
    location.reload();
  }, 1000);
}  