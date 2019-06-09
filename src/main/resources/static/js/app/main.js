   $(document).ready(function() {

                 $("#btn_add").on('click', function() {
                                    $('#new_mess').show();
                                    $('#btn_ok').on('click', function() {
                                        var subject = $('#subject').val();
                                        var email_to = $('#input_email_to').val();
                                        var email_text = $('#input_message').val();
                                        var future_second = $('#input_future_second').val();

                                        var mess = {
                                            subject: subject,
                                            email_to: email_to,
                                            email_text: email_text,
                                            future_second: future_second,
                                            status: "NOT_SENT"
                                        };
                                        $.ajax({
                                            type: 'POST',
                                            url: '/messages/',
                                            data: mess,
                                            success: function() {
                                            alert("success");
                                            },
                                            error: function(e) {
                                            var json = "<h4>Ajax Response</h4><pre>"
                                                                                            + e.responseText + "</pre>";
                                                                                        $('#feedback').html(json);
                                            }
                                        })
                                    })
                                })


                $("#load_table").click(function() {
                    $.ajax({
                        type: 'GET',
                        url: '/messages/',
                        dataType: 'json',
                        cache: false,
                        traditional: true,
                        success: function(response) {
                            for (let i = 0; i < response.length; i++) {
                                $('#my_table').append(
                                    "<tr id='" + response[i].id + "Row'><td>" + response[i].id +
                                    "</td><td>" + response[i].subject +
                                    "</td><td>" + response[i].email_to +
                                    "</td><td>" + response[i].email_text +
                                    "</td><td>" + response[i].future_second +
                                    "</td><td>" + response[i].status +
                                    "</td><td> <button id='btn_delete' data= '" + response[i].id + "' >Delete</button>" + " " +
                                    "<button id='btn_change' data= '" + response[i].id + "' >Change</button></td></tr>");
                            }
                            $("#load_table").prop("disabled", true);

                            $('#my_table').on('click', '#btn_delete', function() {
                                var idBtn = $(this).attr("data");
                                var idRow = idBtn + "Row";
                                $.ajax({
                                    type: 'DELETE',
                                    url: '/messages/' + idBtn,
                                    success: function() {
                                        $('#' + idRow).remove();
                                    }
                                })
                            })

                            $('#my_table').on('click', '#btn_change', function() {
                                var idBtn = $(this).attr("data");

                                $('#change_time').show();
                                $('#change_time').on('click', '#change_btn', function() {
                                    var future_sec = $('#change_date').val();
                                    $.ajax({
                                        type: 'PUT',
                                        url: '/messages/' + idBtn,
                                        data: {
                                            'future_second': future_sec
                                        },
                                        success: function() {
                                        }
                                    })
                                })
                            })
                        }
                    });
                });
            });