GET: $(document).ready(
		function() {

			 
			$("#getAllMessages").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

		 
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "getAllMessages",
					success : function(result) {
						if (result.status == "success") {
							$('#getResultDiv ul').empty();
							var custList = "";
							$.each(result.data,
									function(i, mailSender) {
										var message = "Message id  "
												+ mailSender.id
												+ ", Recipient  = " + mailSender.recipient
												+ ", Subject = " + mailSender.subject
												+", Body = " + mailSender.body
												+", Date = " + mailSender.date
												+ "<br>";
										$('#getResultDiv .list-group').append(
												message)
									});
							console.log("Success: ", result);
						} else {
							$("#getResultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})