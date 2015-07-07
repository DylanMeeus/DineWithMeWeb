/**
 * 
 */

function showAddForm() {

	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "addFriend");

	var userLabel = document.createElement("label");
	userLabel.innerHTML = "Username";
	var userField = document.createElement("input");
	userField.setAttribute("id", "username");
	userLabel.appendChild(userField);
	form.appendChild(userLabel);
	form.appendChild(document.createElement("br"));

	var firstnameLabel = document.createElement("label");
	firstnameLabel.innerHTML = "First name";
	var firstnameField = document.createElement("input");
	firstnameField.setAttribute("id", "firstname");
	firstnameLabel.appendChild(firstnameField);
	form.appendChild(firstnameLabel);

	// Create the button
	var addbutton = document.createElement("input");
	addbutton.type = "button";
	addbutton.value = "add";
	form.appendChild(addbutton);

	document.getElementsByTagName('body')[0].appendChild(form);

}

function openChatHandler() {
	// first load the friends
	getFriendsAsync();
}

var xHRObject = new XMLHttpRequest();
function getFriendsAsync() {
//	alert("Getting friends");
	xHRObject.open("GET", "Servlet?action=friendsAsync", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData() {

	if (xHRObject.status == 200) {
		if (xHRObject.readyState == 4) {
			var serverResponse = xHRObject.responseXML;
			var friendsXML = serverResponse.getElementsByTagName("friend");

			for (var i = 0; i < friendsXML.length; i++) {
				var friendTable = document.getElementById("friendstable");
				var tr = document.createElement("tr");
				var td = (document.createElement("td"));
				var textNode = document
						.createTextNode(friendsXML[i].textContent);
				td.appendChild(textNode);
				tr.appendChild(td);
				friendTable.appendChild(tr);
			}
		}
		
		// THE HANDLER
		addEventHandlers();
	}
	setInterval("getFriendsAsync()", 2000);
}

function addEventHandlers()
{
	var table = document.getElementById("friendstable");
	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		var currentRow = table.rows[i];
		var createClickHandler = function(row) {
			return function() {
				var cell = row.getElementsByTagName("td")[0];
				var id = cell.innerHTML;
				// open new tab
				var win = window.open("Servlet?action=openChat&friend=" + id,
						"_blank");
				if (win) {
					win.focus();
				} else {
					alert("Please allow popups!")
				}
			};
		};

		currentRow.onclick = createClickHandler(currentRow);
	}
}