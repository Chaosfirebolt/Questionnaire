$(function () {
    countdown();
});

function countdown() {
    $("#DateCountdown").TimeCircles({
        "animation": "smooth",
        "bg_width": 1.1,
        "fg_width": 0.06666666666666667,
        "circle_bg_color": "#60686F",
        "time": {
            "Days": {
                "text": "Days",
                "color": "#FFCC66",
                "show": false
            },
            "Hours": {
                "text": "Hours",
                "color": "#99CCFF",
                "show": false
            },
            "Minutes": {
                "text": "Minutes",
                "color": "#BBFFBB",
                "show": false
            },
            "Seconds": {
                "text": "Seconds",
                "color": "#e60f0a",
                "show": true
            }
        }
    });
}