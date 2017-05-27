var ratingsL10n = {
			ajax_url: '/javalog-web/page/rating',
			text_wait: 'Bokunu cikarma a.q !!!',
			image: 'stars',
			image_ext: 'gif',
			max: '5',
			show_loading: '1',
			show_fading: '1',
			custom: '0'
		};
		var ratings_mouseover_image=new Image();
		ratings_mouseover_image.src='/javalog-web/img/rating_over.png';



		var post_id = 0;
		var post_rating = 0;
		var is_being_rated = false;
		ratingsL10n.custom = parseInt(ratingsL10n.custom);
		ratingsL10n.max = parseInt(ratingsL10n.max);
		ratingsL10n.show_loading = parseInt(ratingsL10n.show_loading);
		ratingsL10n.show_fading = parseInt(ratingsL10n.show_fading);
		
		function current_rating(id, rating, rating_text) {
			if (!is_being_rated) {
				post_id = id;
				post_rating = rating;
				if (ratingsL10n.custom && ratingsL10n.max == 2) {
					jQuery('#rating_' + post_id + '_' + rating).attr('src',
							eval('ratings_' + rating + '_mouseover_image.src'));
				} else {
					for (i = 1; i <= rating; i++) {
						if (ratingsL10n.custom) {
							jQuery('#rating_' + post_id + '_' + i).attr('src',
									eval('ratings_' + i + '_mouseover_image.src'));
						} else {
							jQuery('#rating_' + post_id + '_' + i).attr('src',
									ratings_mouseover_image.src);
						}
					}
				}
				if (jQuery('#ratings_' + post_id + '_text').length) {
					jQuery('#ratings_' + post_id + '_text').show();
					jQuery('#ratings_' + post_id + '_text').html(rating_text);
				}
			}
		}
		
		function ratings_off(b) {		
			if (!is_being_rated) {
				for (i = 1; i <= ratingsL10n.max; i++) {
					if (i <= b) {						
						jQuery('#rating_' + post_id + '_' + i).attr('src', '/javalog-web/img/rating_on.png');
					} else {					
						if (i <= (b + 0.5)) {
							jQuery('#rating_' + post_id + '_' + i).attr('src', '/javalog-web/img/rating_half.png');							
						} else {
							jQuery('#rating_' + post_id + '_' + i).attr('src','/javalog-web/img/rating_off.png');
						}
					}
				}
				if (jQuery('#ratings_' + post_id + '_text').length) {
					jQuery('#ratings_' + post_id + '_text').hide();
					jQuery('#ratings_' + post_id + '_text').empty();
				}
			}
		}
		
		function set_is_being_rated(a) {
			is_being_rated = a;
		}
		
		function rate_post_success(a) {
			jQuery('#post-ratings-' + post_id).html(a);
			if (ratingsL10n.show_loading) {
				jQuery('#post-ratings-' + post_id + '-loading').hide();
			}
			if (ratingsL10n.show_fading) {
				jQuery('#post-ratings-' + post_id).fadeTo('def', 1, function() {
					set_is_being_rated(false);
				});
			} else {
				set_is_being_rated(false);
			}
		}
		function rate_post() {
			if (!is_being_rated) {
				set_is_being_rated(true);
				if (ratingsL10n.show_fading) {
					jQuery('#post-ratings-' + post_id).fadeTo('def', 0, function() {
	
						if (ratingsL10n.show_loading) {
							jQuery('#post-ratings-' + post_id + '-loading').show();
						}

						if (typeof Sarissa != 'undefined') {
							 jQuery.ajaxSetup({
								 xhr: function() {
									 if (Sarissa.originalXMLHttpRequest) { 
										 return new Sarissa.originalXMLHttpRequest(); 
										 } 
									 else if (typeof ActiveXObject != 'undefined') { 
										 return new ActiveXObject("Microsoft.XMLHTTP"); 
										 } 
									 else { 
										 return new XMLHttpRequest(); 
										 }
								 } 
							 });
							 }
						
						jQuery.ajax({
							type : 'POST',
							url : ratingsL10n.ajax_url,
							data : 'pid=' + post_id + '&rate=' + post_rating,
							cache : false,
					        error:function(xhr, status, error){
					              alert('hata : ' + error);
					          },
							success : rate_post_success
						});
					});
				} else {
					if (ratingsL10n.show_loading) {
						jQuery('#post-ratings-' + post_id + '-loading').show();
					}
					
					if (typeof Sarissa != 'undefined') {
						 jQuery.ajaxSetup({
							 xhr: function() {
								 if (Sarissa.originalXMLHttpRequest) { 
									 return new Sarissa.originalXMLHttpRequest(); 
									 } 
								 else if (typeof ActiveXObject != 'undefined') { 
									 return new ActiveXObject("Microsoft.XMLHTTP"); 
									 } 
								 else { 
									 return new XMLHttpRequest(); 
									 }
							 } 
						 });
					}

					jQuery.ajax({
						type : 'POST',
						url : ratingsL10n.ajax_url,
						data : 'pid=' + post_id + '&rate=' + post_rating,
						cache : false,
						error:function(xhr, status, error){
				              alert('hata-2 : ' + error);
				          },
						success : rate_post_success
					});
				}
			} else {
				alert(ratingsL10n.text_wait);
			}
		};