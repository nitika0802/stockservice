$(document).ready(function() {

});


function searchClick(){


	var inputValue= $("#inputSearch").val();
	   $.ajax({
        url: "http://localhost:8080/searchservice/webservice/v1?searchTerm="+inputValue
    }).then(function(data) {
		if(data.list.length>0){
		$('#searchTable').remove();
			$("#msg").attr("class","hidden");
		$("#metrics").removeClass("hidden");

		$("#count").text(data.list.length);
			$("#elapsedTimeAlbum").text(data.elapsedTimeAlbum);
				$("#elapsedTimeBooks").text(data.elapsedTimeBook);
		var table = $('<table>').attr("id","searchTable");


		    var row = $('<tr>');
	 var col1 = $('<th>').text("Title");
	  var col2 = $('<th>').text("Author/Artists");
	  var col3 = $('<th>').text("Type");
	    row.append(col1);
	 row.append(col2);
	  row.append(col3);
	table.append(row);

for(i=0; i<data.list.length; i++){
    var row = $('<tr>');
	 var col1 = $('<td>').text(data.list[i].title);
	  var col2 = $('<td>').text(data.list[i].authorOrArtists);
	  var col3 = $('<td>').text(data.list[i].bookOrAlbum);
    row.append(col1);
	 row.append(col2);
	  row.append(col3);
	table.append(row);


$('.searchTable').append(table);
}
}else
{
		$('#searchTable').remove();

		$("#metrics").attr("class","hidden");
			$("#msg").removeClass("hidden");

}
    }).fail(function(jqXHR, textStatus, errorThrown) {
    //handle error here
	$('#searchTable').remove();

		$("#metrics").attr("class","hidden");
			$("#msg").removeClass("hidden");
  });


}