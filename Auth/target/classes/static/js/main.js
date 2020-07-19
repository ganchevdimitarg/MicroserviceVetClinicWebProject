//nav-bar
$(function () {
    'use strict';

    function toggle(e) {
        if (e) e.preventDefault();

        var $this = $(this),
            $navbar = $this.parents('.navbar'),
            $item = $this.parent();

        $('.nav-item.active', $navbar).removeClass('active');
        $item.addClass('active');

        if ($navbar.hasClass('main-nav')) {
            $('.active', $navbar.siblings('.sub-nav')).removeClass('active');
            $($item.data('target')).addClass('active');
        }
    }

    function leave(e) {
        var $this = $(this),
            $navbar = $this.siblings('.main-nav'),
            $subnav = $('.navbar-nav.active', $this);

        $('[data-target="#' + $subnav.attr('id') + '"]', $navbar).removeClass('hover');
        $subnav.removeClass('active');
    };

    function enter(e) {
        var $this = $(this),
            $navbar = $this.parents('.navbar');

        $('.nav-item.hover', $navbar).removeClass('hover');
        $this.addClass('hover');

        if ($navbar.hasClass('main-nav')) {
            $('.active', $navbar.siblings('.sub-nav')).removeClass('active');
            $($this.data('target')).addClass('active');
        }
    }

    $('.main-nav .nav-link, .sub-nav .nav-link').click(toggle);
    $('.main-nav .nav-item').mouseenter(enter);
    $('.sub-nav').mouseleave(leave);
});

// Contact form
$(document).ready(function () {
    "use strict";
    /*==================================================================
    [ Validate ]*/
    var name = $('.validate-input input[name="name"]');
    var email = $('.validate-input input[name="email"]');
    var subject = $('.validate-input input[name="subject"]');
    var message = $('.validate-input textarea[name="message"]');

    $('.validate-form').on('submit', function () {
        var check = true;

        if ($(name).val().trim() == '') {
            showValidate(name);
            check = false;
        }

        if ($(subject).val().trim() == '') {
            showValidate(subject);
            check = false;
        }

        if ($(email).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            showValidate(email);
            check = false;
        }
        if ($(message).val().trim() == '') {
            showValidate(message);
            check = false;
        }
        return check;
    });

    $('.validate-form .input1').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
});

//home switch menu
$(document).ready(function () {
    $("#hiding").click(function () {
        $("#hide").hide();
        $("#showing").show();
    });

    $("#hide-input").hide();
    $("input[name$='user']").click(function () {
        var test = $(this).val();
        if (test === "doctor") {
            $("#hide-input").show();
        } else {
            $("#hide-input").hide();
        }
    });

    $("#hide-update-input").hide();

    $("#hiding-update-info").click(function () {
        $("#hide-update-input").show();
    });


    $(".input-error-massage").hide();
});

//validation login form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='login-form']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            username: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6
            },
            remember: {
                required: true
            },
            logInKey: {
                required: true,
                minlength: 6
            }
        },
        // Specify validation error messages
        messages: {
            username: {
                required: "Please enter your username.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long"
            },
            logInKey: {
                required: "Please provide a key",
                minlength: "Your password must be at least 6 characters long"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

//validation sing up doctor form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='register-form-doctor']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            username: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                minlength: 6
            },
            name: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            specialization: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            description: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            logInKey: {
                required: true,
                minlength: 6
            }
        },
        // Specify validation error messages
        messages: {
            username: {
                required: "Please enter your username.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long - at least one letter and one digit"
            },
            confirmPassword: {
                required: "Please provide a confirmation password",
                minlength: "Your password must be at least 6 characters long - at least one letter and one digit"
            },
            name: {
                required: "Please enter your name.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            specialization: {
                required: "Please enter your specialization.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            description: {
                required: "Please enter your description.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            logInKey: {
                required: "Please provide a key",
                minlength: "Your password must be at least 6 characters long"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
    $("#confirmPassword").keyup(function () {
        if ($("#password").val() !== $("#confirmPassword").val()) {
            $("#msg").html("Password do not match").css("color", "red");
        } else {
            $("#msg").html("Password matched").css("color", "green");
        }
    });
});

//validation sing up user form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='register-form-user']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            username: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6,
            },
            confirmPassword: {
                required: true,
                minlength: 6
            },
            name: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            email: {
                required: true,
                minlength: 10,
                maxlength: 30
            },
            address: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            phoneNumber: {
                required: true,
                minlength: 6,
                maxlength: 20
            }
        },
        // Specify validation error messages
        messages: {
            username: {
                required: "Please enter your username.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long - at least one letter and one digit",
            },
            confirmPassword: {
                required: "Please provide a confirmation password",
                minlength: "Your password must be at least 6 characters long - at least one letter and one digit"
            },
            name: {
                required: "Please enter your name.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            email: {
                required: "Please enter your email.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long",
            },
            address: {
                required: "Please enter your address.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            phoneNumber: {
                required: "Please enter your phone number.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
    $("#confirmPassword-user").keyup(function () {
        if ($("#password-user").val() !== $("#confirmPassword-user").val()) {
            $("#msg").html("Password do not match").css("color", "red");
        } else {
            $("#msg").html("Password matched").css("color", "green");
        }
    });
});

//validation add pet form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='add-pet']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            breed: {
                required: true,
                minlength: 3,
            },
            name: {
                required: true,
                minlength: 3
            },
            age: {
                required: true
            },
            disease: {
                required: true,
            }
        },
        // Specify validation error messages
        messages: {
            breed: {
                required: "Please enter your pet's breed.",
                minlength: "Your password must be at least 3 characters long",
            },
            name: {
                required: "Please enter your name.",
                minlength: "Your password must be at least 3 characters long"
            },
            age: {
                required: "Please enter your pet's age",
            },
            disease: {
                required: "Please enter your pet's disease or None",
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

//validation user update form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='update-user']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            email: {
                required: true,
                minlength: 10,
                maxlength: 30,
            },
            address: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            phoneNumber: {
                required: true,
                minlength: 6,
                maxlength: 20
            }
        },
        // Specify validation error messages
        messages: {
            email: {
                required: "Please enter your email.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long",
            },
            address: {
                required: "Please enter your address.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            phoneNumber: {
                required: "Please enter your phone number.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

//validation add schedule form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='medicine']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            name: {
                required: true,
            },
            description: {
                required: true
            }
        },
        // Specify validation error messages
        messages: {
            name: {
                required: "Please enter name.",
            },
            description: {
                required: "Please enter description.",
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

//validation add schedule form
$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='schedule']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            date: {
                required: true,
                minlength: 10,
                maxlength: 18
            },
            doctor: {
                required: true
            },
            animal: {
                required: true
            }
        },
        // Specify validation error messages
        messages: {
            date: {
                required: "Please enter date.",
            },
            doctor: {
                required: "Please enter doctor.",
            },
            animal: {
                required: "Please enter animal.",
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

jQuery(document).ready(function ($) {

    "use strict";

    // loader
    $(".loader").delay(1000).fadeOut("slow");
    $("#overlayer").delay(1000).fadeOut("slow");


    var siteMenuClone = function () {

        $('.js-clone-nav').each(function () {
            var $this = $(this);
            $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
        });


        setTimeout(function () {

            var counter = 0;
            $('.site-mobile-menu .has-children').each(function () {
                var $this = $(this);

                $this.prepend('<span class="arrow-collapse collapsed">');

                $this.find('.arrow-collapse').attr({
                    'data-toggle': 'collapse',
                    'data-target': '#collapseItem' + counter,
                });

                $this.find('> ul').attr({
                    'class': 'collapse',
                    'id': 'collapseItem' + counter,
                });

                counter++;

            });

        }, 1000);

        $('body').on('click', '.arrow-collapse', function (e) {
            var $this = $(this);
            if ($this.closest('li').find('.collapse').hasClass('show')) {
                $this.removeClass('active');
            } else {
                $this.addClass('active');
            }
            e.preventDefault();

        });

        $(window).resize(function () {
            var $this = $(this),
                w = $this.width();

            if (w > 768) {
                if ($('body').hasClass('offcanvas-menu')) {
                    $('body').removeClass('offcanvas-menu');
                }
            }
        })

        $('body').on('click', '.js-menu-toggle', function (e) {
            var $this = $(this);
            e.preventDefault();

            if ($('body').hasClass('offcanvas-menu')) {
                $('body').removeClass('offcanvas-menu');
                $this.removeClass('active');
            } else {
                $('body').addClass('offcanvas-menu');
                $this.addClass('active');
            }
        })

        // click outisde offcanvas
        $(document).mouseup(function (e) {
            var container = $(".site-mobile-menu");
            if (!container.is(e.target) && container.has(e.target).length === 0) {
                if ($('body').hasClass('offcanvas-menu')) {
                    $('body').removeClass('offcanvas-menu');
                }
            }
        });
    };
    siteMenuClone();


    var sitePlusMinus = function () {
        $('.js-btn-minus').on('click', function (e) {
            e.preventDefault();
            if ($(this).closest('.input-group').find('.form-control').val() != 0) {
                $(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) - 1);
            } else {
                $(this).closest('.input-group').find('.form-control').val(parseInt(0));
            }
        });
        $('.js-btn-plus').on('click', function (e) {
            e.preventDefault();
            $(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) + 1);
        });
    };
    // sitePlusMinus();


    var siteSliderRange = function () {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: 500,
            values: [75, 300],
            slide: function (event, ui) {
                $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
            }
        });
        $("#amount").val("$" + $("#slider-range").slider("values", 0) +
            " - $" + $("#slider-range").slider("values", 1));
    };
    // siteSliderRange();


    var siteCarousel = function () {
        if ($('.nonloop-block-13').length > 0) {
            $('.nonloop-block-13').owlCarousel({
                center: false,
                items: 1,
                loop: true,
                stagePadding: 0,
                margin: 20,
                autoplay: true,
                autoHeight: true,
                nav: true,
                navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">'],
                responsive: {
                    600: {
                        margin: 0,
                        stagePadding: 10,
                        items: 1
                    },
                    1000: {
                        margin: 0,
                        stagePadding: 0,
                        items: 1
                    },
                    1200: {
                        margin: 0,
                        stagePadding: 0,
                        items: 1
                    }
                }
            });
        }

        $('.nonloop-block-13').owlCarousel({
            center: false,
            items: 1,
            loop: true,
            autoplay: true,
            stagePadding: 0,
            margin: 20,
            nav: true,
            navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">'],
            responsive: {
                600: {
                    margin: 0,
                    stagePadding: 0,
                    items: 2
                },
                1000: {
                    margin: 0,
                    stagePadding: 0,
                    items: 2
                },
                1200: {
                    margin: 0,
                    stagePadding: 0,
                    items: 3
                }
            }
        });

        if ($('.slide-one-item').length > 0) {
            $('.slide-one-item').owlCarousel({
                items: 1,
                loop: true,
                stagePadding: 0,
                margin: 0,
                autoplay: true,
                animateOut: 'slideOutDown',
                animateIn: 'fadeIn',
                pauseOnHover: false,
                nav: false,
                dots: true,
                navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">']
            });
        }


        var owl = $('.centernonloop').owlCarousel({
            center: true,
            items: 1,
            loop: true,
            margin: 10,
            dots: true,
            smartSpeed: 1000,
            responsive: {
                600: {
                    items: 3
                }
            }
        });

        $('.customNextBtn').click(function (event) {
            event.preventDefault();
            $('.slide-one-item').trigger('next.owl.carousel');
        });
        $('.customPrevBtn').click(function (event) {
            event.preventDefault();
            $('.slide-one-item').trigger('prev.owl.carousel');
        });

    };
    siteCarousel();

    var siteStellar = function () {
        $(window).stellar({
            responsive: false,
            parallaxBackgrounds: true,
            parallaxElements: true,
            horizontalScrolling: false,
            hideDistantElements: false,
            scrollProperty: 'scroll'
        });
    };
    // siteStellar();

    var siteCountDown = function () {

        if ($('#date-countdown').length > 0) {
            $('#date-countdown').countdown('2020/10/10', function (event) {
                var $this = $(this).html(event.strftime(''
                    + '<span class="countdown-block"><span class="label">%w</span> weeks </span>'
                    + '<span class="countdown-block"><span class="label">%d</span> days </span>'
                    + '<span class="countdown-block"><span class="label">%H</span> hr </span>'
                    + '<span class="countdown-block"><span class="label">%M</span> min </span>'
                    + '<span class="countdown-block"><span class="label">%S</span> sec</span>'));
            });
        }

    };
    siteCountDown();

    var siteDatePicker = function () {

        if ($('.datepicker').length > 0) {
            $('.datepicker').datepicker();
        }

    };
    siteDatePicker();

    var siteSticky = function () {
        if ($(".js-sticky-header").length > 0) {
            $(".js-sticky-header").sticky({topSpacing: 0});
        }
    };
    siteSticky();

    // navigation
    var OnePageNavigation = function () {
        var navToggler = $('.site-menu-toggle');
        $("body").on("click", ".main-menu li a[href^='#'], .smoothscroll[href^='#'], .site-mobile-menu .site-nav-wrap li a[href^='#']", function (e) {
            e.preventDefault();

            var hash = this.hash;

            $('html, body').animate({
                'scrollTop': $(hash).offset().top - 0
            }, 1000, 'easeInOutCirc', function () {
                window.location.hash = hash;
                setTimeout(function () {
                    $('body').removeClass('offcanvas-menu');
                }, 20);

            });

        });
    };
    OnePageNavigation();

    var siteScroll = function () {

        $(window).scroll(function () {

            var st = $(this).scrollTop();

            if (st > 300) {
                $('.js-sticky-header').addClass('shrink');
            } else {
                $('.js-sticky-header').removeClass('shrink');
            }

            if ($('body').hasClass('offcanvas-menu')) {
                $('body').removeClass('offcanvas-menu');
            }

        })

    };
    siteScroll();

    var siteIstotope = function () {
        /* activate jquery isotope */
        var $container = $('#posts').isotope({
            itemSelector: '.item',
            isFitWidth: true
        });

        $(window).resize(function () {
            $container.isotope({
                columnWidth: '.col-sm-3'
            });
        });

        $container.isotope({filter: '*'});

        // filter items on button click
        $('#filters').on('click', 'button', function (e) {
            e.preventDefault();
            var filterValue = $(this).attr('data-filter');
            $container.isotope({filter: filterValue});
            $('#filters button').removeClass('active');
            $(this).addClass('active');
        });
    }

    siteIstotope();

    $('.fancybox').on('click', function () {
        var visibleLinks = $('.fancybox');

        $.fancybox.open(visibleLinks, {}, visibleLinks.index(this));

        return false;
    });

    var stickyFillInit = function () {
        $(window).on('resize orientationchange', function () {
            recalc();
        }).resize();

        function recalc() {
            if ($('.jm-sticky-top').length > 0) {
                var elements = $('.jm-sticky-top');
                Stickyfill.add(elements);
            }
        }
    }
    stickyFillInit();

});

(function ($) {
    "use strict"; // Start of use strict

    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html, body').animate({
                    scrollTop: (target.offset().top - 56)
                }, 1000, "easeInOutExpo");
                return false;
            }
        }
    });

    // Closes responsive menu when a scroll trigger link is clicked
    $('.js-scroll-trigger').click(function () {
        $('.navbar-collapse').collapse('hide');
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $('body').scrollspy({
        target: '#mainNav',
        offset: 56
    });

})(jQuery); // End of use strict

$(document).ready(function(){
    $("#update").click(function(){
        $("#hide-update-input").hide();
    });
    $("#hiding-update-info").click(function(){
        $("#hide-update-input").show();
    });
});
