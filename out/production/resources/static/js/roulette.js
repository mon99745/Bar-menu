Array.prototype.remove = function(elem) {
    this.some(function(e, i, a) {
        if (e === elem) {
            a.splice(i, 1);
            return true;
        }
        return false;
    });
    return this;
};
Array.prototype.removeAll = function(elem) {
    this.forEach(function(e, i, a) {
        if (e === elem) {
            a.splice(i, 1);
        }
    });
    return this;
}

var colorPalette = [
    '#f44336',
    '#e91e63',
    '#9c27b0',
    '#673ab7',
    '#3f51b5',
    '#2196f3',
    '#03a9f4',
    '#00bcd4',
    '#009688',
    '#4caf50',
    '#8bc34a',
    '#cddc39',
    '#ffeb3b',
    '#ffc107',
    '#ff9800',
    '#ff5722',
    '#795548',
//  '#9e9e9e',
    '#607d8b',
];
(function() {
    var startColorIdx = Math.floor(Math.random() * colorPalette.length);
    var startColor = colorPalette[startColorIdx];
    colorPalette.remove(startColor);
    window.getColor = function(i) {
        if (i == 0) return startColor;
        return colorPalette[ Math.floor(startColorIdx + 4 * i) % colorPalette.length ];
    };
})();

// -------------------------------------------------------------------------------------------




// -------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------


var noname = '이름 없음';
var font = '32px Jua';
var font2 = font; //'36px Sans-serif';
var line_color = '#fff';
var bgColor = 'black';
var pinColor = 'red'
var fontStrokeStyle = '#37474f';



function DEG2RAD(degree) {
    return (degree * Math.PI) / 180;
}

$(document).ready(function() {
    var $li = $('li.item');
    var $cl = $li.clone();
    window.createItem = function() {
        $('ul').append($cl.clone());
    }
});


function updateData() {

    var items = [];
    var angleSum = 0;
    var weightSum = 0;

    $('li.item').each(function(i, e) {
        var $e = $(e);
        var $title = $e.find('.item-title');
        if ($title.text()) {
            $e.removeClass('empty');
        } else {
            $e.addClass('empty');
            return;
        }
        var weight = parseFloat($e.find('.item-weight').attr('data-weight')) || 0;
        weightSum += weight;
    });
    $('li.item:not(.empty)').each(function(i, e) {
        var $e = $(e);

        var title = $e.find('.item-title').text();
        if (!title) {
            return;
        }
        var weight = parseFloat($e.find('.item-weight').attr('data-weight')) || 0;
        var chance = weight / weightSum;
        $e.attr('data-chance', (chance * 100).toFixed(2));

        var angle = 360 * chance;
        angleSum += angle;

        var color = getColor(i)
        items.push({
            name: title,
            angle: angle,
            angleSum: angleSum,
            color: color
        });


    });

    R.Items = items;
    R.dirty = true;
}

function cleanList() {
    var empty = [];
    $('li').each(function() {
        var $this = $(this);
        var $title = $this.find('.item-title');
        var text = $title.text().trim();
        $title.text(text);
        if (!text) {
            empty.push($this);
        }
    });

    if (empty.length === 0) {
        // create new li
        createItem();
    } else if (empty.length > 1) {
        empty.pop();
        empty.forEach(function($e) {
            $e.remove();
        });
    }
}

$(document).on('blur', '.item-title', function(evt) {
    cleanList();
    updateData();
});
$(document).on('keyup', '.item-title', function(evt) {
    updateData();
});

$(document).on('click', 'li.item', function(evt) {
    $(evt.currentTarget).find('.item-title').focus();
});

$(document).on('click', '.item-weight-add, .item-weight-sub', function(evt) {
    evt.stopPropagation();
    var $this = $(evt.currentTarget);
    var sign = $this.is('.item-weight-add') ? +1 : $this.is('.item-weight-sub') ? -1 : 0;
    var $w = $this.focus().siblings('.item-weight');
    var amount = evt.ctrlKey && !evt.shiftKey &&  evt.altKey ? 1000 :
        !evt.ctrlKey && !evt.shiftKey &&  evt.altKey ? 100 :
            evt.ctrlKey && !evt.shiftKey && !evt.altKey ? 10 :
                1;
    var w = Math.floor(Math.max(1, Math.min(9999, (parseInt($w.attr('data-weight')) || 1) + sign * amount)));
    $w.attr('data-weight', w);
    updateData();
});

$(document).on('keydown', function(evt) {
    var $target = $(evt.target);
    if (evt.key === 'Enter') {
        evt.preventDefault();
        cleanList();
        if ($target.is('.item-title')) {
            $target.blur()
                .closest('li')
                .next('li')
                .find('.item-title').focus();
        }
        if ($target.is('[contenteditable]')) {
            $target.blur();
        }
    } else if (evt.key === 'Escape') {
        if ($target.is('[contenteditable]')) {
            $target.blur();
        }
    }
});


// $(document).on('keyup', '[contenteditable]', function(evt) {
//  updateData();
// });

function Roulette() {

    var R = {
        dirty: true,
        rotation: 0,
        $button: $('#roll')
    };
    window.R = R;

    function OnStart() {
        try {

            R.canvas = document.querySelector('canvas');
            R.ctx = R.canvas.getContext('2d', true);
            R.size = { width: R.canvas.width, height: R.canvas.height };
            R.center = { x: R.size.width / 2 , y: R.size.height / 2 };

            window.radius = Math.min(Math.min(R.size.width, R.size.height) * 0.5 * 0.9, R.size.height / 2 - 40);

            R.bufferCanvas = document.createElement('canvas');
            R.bufferCanvas.width = R.size.width;
            R.bufferCanvas.height = R.size.height;
            R.buffer = R.bufferCanvas.getContext('2d', true);
            R.buffer.textAlign='center';

            requestAnimationFrame(OnUpdate);
        } catch(e) { console.log(e); }
    }

    function OnUpdate(timestamp) {
        R.deltaTime = timestamp - (R.lastTimestamp || timestamp);
        //R.FPS = (R.deltaTime && (R.deltaTime > 0)) ? Math.round(1000 / R.deltaTime) : 0;

        requestAnimationFrame(OnUpdate);
        if (0 < R.deltaTime && R.deltaTime < 17) {
            return;
        }
        // this frame
        //R.$button.blur();
        //console.log(R.FPS);
        if (R.stopping) {
            if (Date.now() - R.stopping > 15 * 1000) {
                R.angularVelocity = 0;
                delete R.stopping;
            }
        }
        if (R.angularDamping) {
            R.angularVelocity -= Math.max(20, Math.min(R.angularVelocity * 0.5, R.angularDamping)) * (R.deltaTime / 1000);
        }
        if (R.angularVelocity <= 0) {
            R.angularVelocity = 0;
            if (R.launched && typeof R.onStop === 'function') {
                R.launched = false;
                R.onStop();
            }
        }
        if (R.angularVelocity) {
            R.rotation += R.angularVelocity * (R.deltaTime / 1000);
            while (R.rotation < 0) R.rotation += 360;
            while (R.rotation >= 360) R.rotation -= 360;
        }
        if (R.prevRotation != R.rotation) {
            R.dirty = true;
        }
        R.prevRotation = R.rotation;

        // render
        OnRender();

        // next frame
        R.lastTimestamp = timestamp;
        // requestAnimationFrame(OnUpdate);
    }

    function drawArc(option, name, split) {
        if (!split || (split && !name)) {
            R.buffer.save();
            R.buffer.translate(option.x, option.y);
            R.buffer.rotate(DEG2RAD(option.rotation - 90));

            R.buffer.beginPath();
            R.buffer.moveTo(0, 0);
            R.buffer.arc(0, 0, option.radius, 0, DEG2RAD(option.angle));
            R.buffer.closePath();
            R.buffer.fillStyle = option.color;
            R.buffer.fill();

            R.buffer.beginPath();
            R.buffer.arc(0, 0, option.radius, 0, DEG2RAD(option.angle));
            R.buffer.strokeStyle = window.line_color;
            R.buffer.lineWidth = 3;
            R.buffer.stroke();

            R.buffer.restore();
        }
        if (!split || (split && name)) {
            // text
            R.buffer.save();
            R.buffer.translate(option.x, option.y);
            R.buffer.rotate(DEG2RAD(option.rotation - 90 + option.angle / 2));

            R.buffer.beginPath();
            R.buffer.moveTo(0, 0);
            R.buffer.font = window.font2;
            R.buffer.strokeStyle = fontStrokeStyle; // 'black';
            R.buffer.lineWidth = 5;
            R.buffer.strokeText(name, option.radius / 2, 8);
            //R.buffer.fillStyle = window.line_color;
            R.buffer.fillStyle = 'white';
            R.buffer.fillText(name, option.radius / 2, 8);

            R.buffer.restore();
        }
    }

    function OnRender() {
        if (!R.dirty) return;
        R.dirty = false;

        // clear
        R.buffer.fillStyle = bgColor;
        R.buffer.fillRect(0, 0, R.size.width, R.size.height);
        //R.buffer.clearRect(0, 0, R.size.width, R.size.height);

        if (R.FPS) {
            R.buffer.font = window.font;
            R.buffer.fillStyle = window.line_color;
            R.buffer.fillText(R.FPS + 'FPS', 50, R.center.y - window.radius - 7);
        }

        // shadow
        R.buffer.shadowBlur = 15;
        R.buffer.shadowColor = 'black';
        drawArc({ x: R.center.x, y: R.center.y + 15 + 5, radius: window.radius - 5, rotation: 0, angle: 360, color: bgColor }, '');
        R.buffer.shadowBlur = 0;
        drawArc({ x: R.center.x, y: R.center.y + 15, radius: window.radius, rotation: 0, angle: 360, color: bgColor }, '');

        //
        R.current = null;
        if (R.Items && R.Items.length > 0) {
            R.lastAngle = 0;
            R.Items.forEach(function(item) {
                R.currentAngle = 360 - R.rotation;
                while (R.currentAngle < 0) R.currentAngle += 360;
                while (R.currentAngle >= 360) R.currentAngle -= 360;
                // if (item.angleSum - item.angle <= R.currentAngle && R.currentAngle <= item.angleSum) {
                //  R.current = item.name;
                // }

                drawArc({ x: R.center.x, y: R.center.y + 15, radius: window.radius,
                    rotation: R.rotation + R.lastAngle, angle: item.angle, color: item.color }, null, true);
                R.lastAngle += item.angle;
                //if (R.lastAngle >= 360) R.lastAngle -= 360;
            });
            R.lastAngle = 0;
            R.Items.forEach(function(item) {
                R.currentAngle = 360 - R.rotation;
                while (R.currentAngle < 0) R.currentAngle += 360;
                while (R.currentAngle >= 360) R.currentAngle -= 360;
                if (item.angleSum - item.angle <= R.currentAngle && R.currentAngle <= item.angleSum) {
                    R.current = item.name;
                    R.currentColor = item.color;
                }

                drawArc({ x: R.center.x, y: R.center.y + 15, radius: window.radius,
                    rotation: R.rotation + R.lastAngle, angle: item.angle, color: item.color }, item.name, true);
                R.lastAngle += item.angle;
                //if (R.lastAngle >= 360) R.lastAngle -= 360;
            });
        } else {
            drawArc({ x: R.center.x, y: R.center.y + 15, radius: window.radius,
                rotation: R.rotation-45, angle: 180, color: getColor(0) }, '돌려돌려');
            drawArc({ x: R.center.x, y: R.center.y + 15, radius: window.radius,
                rotation: R.rotation-45+180, angle: 180, color: getColor(1) }, '돌림판');
        }
        // pin
        R.buffer.beginPath();
        R.buffer.moveTo(R.size.width / 2    , R.center.y - window.radius + 20);
        R.buffer.lineTo(R.size.width / 2 - 5, R.center.y - window.radius + 5 );
        R.buffer.lineTo(R.size.width / 2 - 5, R.center.y - window.radius + 0 );
        R.buffer.lineTo(R.size.width / 2 + 5, R.center.y - window.radius + 0 );
        R.buffer.lineTo(R.size.width / 2 + 5, R.center.y - window.radius + 5 );
        R.buffer.closePath();
        R.buffer.fillStyle = window.pinColor;
        R.buffer.fill();
        R.buffer.stroke();

        if (R.current) {
            //R.buffer.shadowBlur = 1;
            R.buffer.font = window.font;
            if (R.currentColor) {
                R.buffer.strokeStyle = window.line_color; // 'black';
                R.buffer.lineWidth = 5;
                R.buffer.strokeText(R.current, R.size.width / 2, R.center.y - window.radius - 7);
            }
            R.buffer.fillStyle = R.currentColor || window.line_color;
            R.buffer.fillText(R.current, R.size.width / 2, R.center.y - window.radius - 7);
            //R.buffer.shadowBlur = 0;
        }
        // 가운데 점
        // R.buffer.beginPath();
        // R.buffer.arc(R.center.x, R.center.y + 15, 3, 0, 2 * Math.PI);
        // R.buffer.fill();
        R.ctx.drawImage(R.bufferCanvas, 0, 0);
    }

    R.Launch = function() {
        R.launched = true;
        R.angularVelocity = 4 * 360; // 4 * 360;
        R.angularDamping = 0;
        delete R.stopping;
    }
    R.Stop = function() {
        R.stopping = Date.now();
        R.angularDamping = 360 * (0.5 + 0.1 * Math.random()); // 360 * (2 + Math.random());
    }

    OnStart();
}

$(document).ready(function() {
    Roulette();

    var $result = $('#result');
    var $roll = $('#roll');
    var it;

    R.onStop = function() {
        $result.removeClass('out').text(R.current || '돌려돌려 돌림판!').show().addClass('play');
        clearTimeout(it);
        it = setTimeout(function() {
            $roll.fadeIn(600, function() {
                lock(false);
                $result.hide();
            });
        }, 1500);
    }
});

function lock(b) {
    if (b) {
        $('[contenteditable]').attr('contenteditable', false);
        $('ul').addClass('lock');
    } else {
        $('[contenteditable]').attr('contenteditable', true);
        $('ul').removeClass('lock');
    }
}

$(document).on('click', '#roll', function(evt) {
    if (!R.isRolling) {
        R.isRolling = true;
        R.Launch();
        $(evt.currentTarget).focus().addClass('active');
        lock(true);
    } else {
        R.isRolling = false;
        R.Stop();
        $(evt.currentTarget).focus().removeClass('active').hide();
    }
});

$(document).on('click', '.item-delete', function(evt) {
    $(evt.currentTarget).closest('li').remove();
    updateData();
    cleanList();
});

// $(document).on('click', function(evt) {
//  $(evt.target).focus();
//  if ($(evt.target).is('h1')) {
//      $(evt.target).attr('contenteditable', 'true');
//  } else {
//      $('[contenteditable]').removeAttr('contenteditable');
//  }
// });

$(document).on('keydown', 'h1[contenteditable]', function(e) {
    if (e.key === 'Enter') {
        e.preventDefault();
    }
});

(function() {
    var $t = $('#button-tooltip');
    var $u = $('ul');

    function show() {
        $t.show();
    };
    function hide() {
        $t.hide();
    };
    function move() {
        var o = $u.offset();
        o.top -= 100;
        o.left += 100;
        $t.offset(o);
    };

    $(document).on('mouseenter mousemove', '.item-weight-sub, .item-weight-add', function(e) {
        move();
        show();
    }).on('mouseleave', '.item-weight-sub, .item-weight-add', function(e) {
        hide();
    });

})();