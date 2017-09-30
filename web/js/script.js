(function ($) {
    $(function () {
        //Inicialización
        $('.carousel-logos.carousel').carousel();
        $('.slider').slider();
        $('.parallax').parallax();
        $('select').material_select();

        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 300, // Creates a dropdown of 15 years to control year
            clear: 'Clear',
            close: 'Ok',
            closeOnSelect: false // Close upon selecting a date,
        });

        $(".button-collapse").sideNav();
        $('#search-nav').hide();

        $('.carousel.carousel-slider').carousel({
            fullWidth: true
        });

        $('.scrollspy').scrollSpy({
            scrollOffset: 64
        });

        //Mostrar u ocultar barra de busqueda
        $('#search-nav-button').click(function () {
            $('#principal-nav').hide(1000);
            $('#search-nav').show(1000);
        });
        $('#search-nav-cancel').click(function () {
            $('#search-nav').hide(1000);
            $('#principal-nav').show(1000);
        });

        //Cambiar de logo productos principales
        setInterval(function () {
            $('.carousel-logos').carousel('next', 1);
        }, 2000);
        //Cambiar de logo productos principales
        setInterval(function () {
            $('.carousel.carousel-slider').carousel('next', 1);
        }, 5000);

        if ($('.pushpin-nav').length) {
            $('.pushpin-nav').each(function () {
                var $this = $(this);
                var $target = $('#' + $(this).attr('data-target'));
                $this.pushpin({
                    top: $target.offset().top,
                    bottom: $target.offset().top + $target.outerHeight() - $this.height()
                });
            });
        }
    });
    
})(jQuery);
