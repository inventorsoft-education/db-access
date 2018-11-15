$(document).ready(
		function() {

			 
			$("#messageForm").submit(function(event) {
				 
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				 
				var formData = {
					MessageId : $("#id").val(),
					Send to : $("#recipient").val(),
					Subject : $("#subject").val(),
					Text : $("#body").val(),
					Message date : $("#date").val()
				}

				 
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "saveMessage",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									"" + result.data.subject
											+ "Post Successfully! <br>"
											);
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})