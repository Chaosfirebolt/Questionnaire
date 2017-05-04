$(function () {
    let usernameSearch = $('#username-input');

    usernameSearch.on('input', findByUsername);
    usernameSearch.on('keyup', removeResults);
});

function findByUsername() {
    let username = $('#username-input').val();
    $.ajax({
        type: 'get',
        url: '/ranking/user-data',
        data: {
            username: username
        },
        dataType: 'json',
        contentType: 'application/json',
        success: function(users) {
            let usersList = $('#users-list');
            usersList.empty();
            usersList.append(
                $('<div></div>').addClass('container form-group row')
                    .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly')
                        .css("background-color","darkgray").val('User'))
                    .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly')
                        .css("background-color","darkgray").val('Answered'))
                    .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly')
                        .css("background-color","darkgray").val('Total time'))
            );
            $.each(users, function (index, user) {
                addUserToDom(user);
            })
        }
    });
}

function addUserToDom(user) {
    let username = user.username;
    let answered = user.currentQuestion;
    let totalTime = user.totalTime;
    $('#users-list').append(
        $('<div></div>').addClass('container form-group row')
            .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly').val(username))
            .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly').val(answered))
            .append($('<input/>').addClass('form-control col-lg-2').attr('readonly', 'readonly').val(totalTime))
    );
}

function removeResults(key) {
    if (key.which == 27) {
        $('#users-list').empty();
    }
}