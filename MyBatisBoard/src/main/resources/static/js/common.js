function ajaxCmm(type, url, data, callback) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        success: function(data, status, xr) {
            return callback(data);
        },
        error: function(xhr, status, error) {
            return callback(data)
        }
    });
}


