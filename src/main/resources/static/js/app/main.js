$(document).ready(function() {
                 $("#btn_add").on('click', function() {
                                    $('#new_mess').show();
                                    $(document).on('submit','form#contact-form', function(e) {
                                        e.preventDefault();
                                        var subject = $('#subject').val();
                                        var email_to = $('#input_email_to').val();
                                        var email_text = $('#input_message').val();
                                        var time_stamp = $('#time_stamp').val();

                                        var mess = {
                                            subject: subject,
                                            emailTo: email_to,
                                            emailText: email_text,
                                            timeStamp: time_stamp,
                                            status: false
                                        };
                                        $.ajax({
                                            type: 'POST',
                                            url: '/messages/',
                                            data: mess,
                                            success: function() {
                                                    $(feedback).remove();
                                                    location.reload();
                                            },
                                            error: function(e) {
                                                        var error = "<h4>Ajax Response error</h4>"
                                                        + e.responseText + "</pre>";
                                                        $('#feedback').html(error);
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
                                    "</td><td>" + response[i].emailTo +
                                    "</td><td>" + response[i].emailText +
                                    "</td><td>" + response[i].timeStamp +
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
                                            'time': future_sec
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