/*
jquery.percentageloader.js 
 
Copyright (c) 2012, Better2Web
All rights reserved.

This jQuery plugin is licensed under the Simplified BSD License. Please
see the file license.txt that was included with the plugin bundle.

 */

/*global jQuery */

(function($) {
	/* Strict mode for this plugin */
	"use strict";
	/* jslint browser: true */

	/* Our spiral gradient data */
	var imgdata = "data:image/png;base64,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", gradient = new Image();
	gradient.src = imgdata;

	/**
	 * Percentage loader
	 * 
	 * @param params
	 *            Specify options in {}. May be on of width, height, progress or
	 *            value.
	 * 
	 * @example $("#myloader-container).percentageLoader({ width : 256, // width
	 *          in pixels height : 256, // height in pixels progress: 0, //
	 *          initialise progress bar position, within the range [0..1] value:
	 *          '0kb' // initialise text label to this value });
	 */
	$.fn.percentageLoader = function(params) {
		var settings, canvas, percentageText, valueText, items, i, item, selectors, s, ctx, progress, value, cX, cY, lingrad, innerGrad, tubeGrad, innerRadius, innerBarRadius, outerBarRadius, radius, startAngle, endAngle, counterClockwise, completeAngle, setProgress, setValue, applyAngle, drawLoader, clipValue, outerDiv;
		/* Specify default settings */
		settings = {
			width : 256,
			height : 256,
			progress : 0,
			value : '0kb',
			status : '正常',// 状态 曾 20141105 
			controllable : false
		};

		/* Override default settings with provided params, if any */
		if (params !== undefined) {
			$.extend(settings, params);
		} else {
			params = settings;
		}

		outerDiv = document.createElement('div');
		outerDiv.style.width = settings.width + 'px';
		outerDiv.style.height = settings.height + 'px';
		outerDiv.style.position = 'relative';

		$(this).append(outerDiv);

		/* Create our canvas object */
		canvas = document.createElement('canvas');
		canvas.setAttribute('width', settings.width);
		canvas.setAttribute('height', settings.height);
		outerDiv.appendChild(canvas);

		/*
		 * 曾凡 20140512 创建
		 * 
		 */
		var percentageTextUp = document.createElement('div');
		percentageTextUp.style.width = (settings.width.toString() - 2) + 'px';
		percentageTextUp.style.textAlign = 'center';
		percentageTextUp.style.height = '0px';
		percentageTextUp.style.left = 0;
		percentageTextUp.style.position = 'absolute';
		/*
		 * Create div elements we'll use for text. Drawing text is possible with
		 * canvas but it is tricky working with custom fonts as it is hard to
		 * guarantee when they become available with differences between
		 * browsers. DOM is a safer bet here
		 */
		percentageText = document.createElement('div');
		percentageText.style.width = (settings.width.toString() - 2) + 'px';
		percentageText.style.textAlign = 'center';
		percentageText.style.height = '50px';
		percentageText.style.left = 0;
		percentageText.style.position = 'absolute';

		valueText = document.createElement('div');
		valueText.style.width = (settings.width - 2).toString() + 'px';
		valueText.style.textAlign = 'center';
		valueText.style.height = '0px';
		valueText.style.overflow = 'hidden';
		valueText.style.left = 0;
		valueText.style.position = 'absolute';

		/* Force text items to not allow selection */
		items = [ valueText, percentageText ];
		for (i = 0; i < items.length; i += 1) {
			item = items[i];
			selectors = [ '-webkit-user-select', '-khtml-user-select',
					'-moz-user-select', '-o-user-select', 'user-select' ];

			for (s = 0; s < selectors.length; s += 1) {
				$(item).css(selectors[s], 'none');
			}
		}

		/* Add the new dom elements to the containing div */
		outerDiv.appendChild(percentageTextUp);
		outerDiv.appendChild(percentageText);
		outerDiv.appendChild(valueText);

		/* Get a reference to the context of our canvas object */
		ctx = canvas.getContext("2d");

		/* Set various initial values */

		/* Centre point */
		cX = (canvas.width / 2) - 1;
		cY = (canvas.height / 2) - 1;

		/* Create our linear gradient for the outer ring */
		lingrad = ctx.createLinearGradient(cX, 0, cX, canvas.height);
		lingrad.addColorStop(0, '#d6eeff');
		lingrad.addColorStop(1, '#b6d8f0');

		/* Create inner gradient for the outer ring */
		innerGrad = ctx.createLinearGradient(cX, cX * 0.133333, cX,
				canvas.height - cX * 0.133333);
		innerGrad.addColorStop(0, '#f9fcfe');
		innerGrad.addColorStop(1, '#d9ebf7');

		/* Tube gradient (background, not the spiral gradient) */
		tubeGrad = ctx.createLinearGradient(cX, 0, cX, canvas.height);
		tubeGrad.addColorStop(0, '#c1dff4');
		tubeGrad.addColorStop(1, '#aacee6');

		/* The inner circle is 2/3rds the size of the outer one */
		innerRadius = cX * 0.6666;
		/*
		 * Outer radius is the same as the width / 2, same as the centre x (but
		 * we leave a little room so the borders aren't truncated)
		 */
		radius = cX - 2;

		/* Calculate the radii of the inner tube */
		innerBarRadius = innerRadius + (cX * 0.06);
		outerBarRadius = radius - (cX * 0.06);

		/* Bottom left angle */
		startAngle = 2.1707963267949;
		/* Bottom right angle */
		endAngle = 0.9707963267949 + (Math.PI * 2.0);

		/*
		 * Nicer to pass counterClockwise / clockwise into canvas functions than
		 * true / false
		 */
		counterClockwise = false;

		/* Borders should be 1px */
		ctx.lineWidth = 1;

		/**
		 * Little helper method for transforming points on a given angle and
		 * distance for code clarity
		 */
		applyAngle = function(point, angle, distance) {
			return {
				x : point.x + (Math.cos(angle) * distance),
				y : point.y + (Math.sin(angle) * distance)
			};
		};

		/**
		 * render the widget in its entirety.
		 */
		drawLoader = function() {
			/* Clear canvas entirely */
			ctx.clearRect(0, 0, canvas.width, canvas.height);

			/** * IMAGERY ** */

			/* draw outer circle */
			ctx.fillStyle = lingrad;
			ctx.beginPath();
			ctx.strokeStyle = '#b2d5ed';
			ctx.arc(cX, cY, radius, 0, Math.PI * 2, counterClockwise);
			ctx.fill();
			ctx.stroke();

			/* draw inner circle */
			ctx.fillStyle = innerGrad;
			ctx.beginPath();
			ctx.arc(cX, cY, innerRadius, 0, Math.PI * 2, counterClockwise);
			ctx.fill();
			ctx.strokeStyle = '#b2d5edaa';
			ctx.stroke();

			ctx.beginPath();

			/**
			 * Helper function - adds a path (without calls to beginPath or
			 * closePath) to the context which describes the inner tube. We use
			 * this for drawing the background of the inner tube (which is
			 * always at 100%) and the progress meter itself, which may vary
			 * from 0-100%
			 */
			function makeInnerTubePath(startAngle, endAngle) {
				var centrePoint, startPoint, controlAngle, capLength, c1, c2, point1, point2;
				centrePoint = {
					x : cX,
					y : cY
				};

				startPoint = applyAngle(centrePoint, startAngle, innerBarRadius);

				ctx.moveTo(startPoint.x, startPoint.y);

				point1 = applyAngle(centrePoint, endAngle, innerBarRadius);
				point2 = applyAngle(centrePoint, endAngle, outerBarRadius);

				controlAngle = endAngle + (3.142 / 2.0);
				/* Cap length - a fifth of the canvas size minus 4 pixels */
				capLength = (cX * 0.20) - 4;

				c1 = applyAngle(point1, controlAngle, capLength);
				c2 = applyAngle(point2, controlAngle, capLength);

				ctx.arc(cX, cY, innerBarRadius, startAngle, endAngle, false);
				ctx.bezierCurveTo(c1.x, c1.y, c2.x, c2.y, point2.x, point2.y);
				ctx.arc(cX, cY, outerBarRadius, endAngle, startAngle, true);

				point1 = applyAngle(centrePoint, startAngle, innerBarRadius);
				point2 = applyAngle(centrePoint, startAngle, outerBarRadius);

				controlAngle = startAngle - (3.142 / 2.0);

				c1 = applyAngle(point2, controlAngle, capLength);
				c2 = applyAngle(point1, controlAngle, capLength);

				ctx.bezierCurveTo(c1.x, c1.y, c2.x, c2.y, point1.x, point1.y);
			}

			/* Background tube */
			ctx.beginPath();
			ctx.strokeStyle = '#bcd4e5';
			makeInnerTubePath(startAngle, endAngle);

			ctx.fillStyle = tubeGrad;
			ctx.fill();
			ctx.stroke();

			/* Calculate angles for the the progress metre */
			completeAngle = startAngle + (progress * (endAngle - startAngle));

			ctx.beginPath();
			makeInnerTubePath(startAngle, completeAngle);

			/* We're going to apply a clip so save the current state */
			ctx.save();
			/* Clip so we can apply the image gradient */
			ctx.clip();

			/* Draw the spiral gradient over the clipped area */
			ctx.drawImage(gradient, 0, 0, canvas.width, canvas.height);

			/* Undo the clip */
			ctx.restore();

			/* Draw the outline of the path */
			ctx.beginPath();
			makeInnerTubePath(startAngle, completeAngle);
			ctx.stroke();

			/** * TEXT ** */
			(function() {
				var fontSize, string, smallSize, heightRemaining;
				/* Calculate the size of the font based on the canvas size */
				fontSize = cX / 5;

				percentageText.style.top = (((settings.height + 20) / 2) - (fontSize / 2))
						.toString()
						+ 'px';
				percentageText.style.color = '#80a9c8';
				percentageText.style.font = (fontSize - 2).toString()
						+ 'px BebasNeueRegular';
				percentageText.style.textShadow = '0 1px 1px #FFFFFF';
				/*
				 * 曾凡 20140610 curValue/maxValue = curPerValue string =
				 * maxValue*curPerValue
				 */
//				var maxValue = 5.0;
//				var unit = "ppm";
//				var status = "正常";
				/* Calculate the text for the given percentage */
//				string = (progress * maxValue).toFixed(0) + unit;
//				percentageText.innerHTML = string;

				percentageTextUp.style.top = (((settings.height - 20) / 2) - (fontSize / 2))
						.toString()
						+ 'px';
				percentageTextUp.style.color = '#80a9c8';
				percentageTextUp.style.font = fontSize.toString()
						+ 'px BebasNeueRegular';
				percentageTextUp.style.textShadow = '0 1px 1px #FFFFFF';
				percentageTextUp.innerHTML = status;

				/* Calculate font and placement of small 'value' text */
				smallSize = cX / 5.5;
				valueText.style.color = '#80a9c8';
				/* 曾凡 20140612 font-4 */
				valueText.style.font = (smallSize - 4).toString()
						+ 'px BebasNeueRegular';
				valueText.style.height = smallSize.toString() + 'px';
				valueText.style.textShadow = 'None';

				/*
				 * Ugly vertical align calculations - fit into bottom ring. The
				 * bottom ring occupes 1/6 of the diameter of the circle
				 */
				heightRemaining = (settings.height * 0.16666666) - smallSize;
				valueText.style.top = ((settings.height * 0.8333333) + (heightRemaining / 4))
						.toString()
						+ 'px';
			}());
		};

		/**
		 * Check the progress value and ensure it is within the correct bounds
		 * [0..1]
		 */
		clipValue = function() {
			if (progress < 0) {
				progress = 0;
			}

			if (progress > 1.0) {
				progress = 1.0;
			}
		};

		/*
		 * Sets the current progress level of the loader
		 * 
		 * @param value the progress value, from 0 to 1. Values outside this
		 * range will be clipped
		 */
		setProgress = function(value) {
			/* Clip values to the range [0..1] */
			progress = value;
			clipValue();
			drawLoader();
		};

		this.setProgress = setProgress;

		setValue = function(val,status) {
			percentageTextUp.innerHTML = status;
			value = val;
			valueText.innerHTML = value;
		};

		this.setValue = setValue;
		this.setValue(settings.value,settings.status);

		progress = settings.progress;
		clipValue();

		/* Do an initial draw */
		drawLoader();

		/* In controllable mode, add event handlers */
		if (params.controllable === true) {
			(function() {
				var mouseDown, getDistance, adjustProgressWithXY;
				getDistance = function(x, y) {
					return Math.sqrt(Math.pow(x - cX, 2) + Math.pow(y - cY, 2));
				};

				mouseDown = false;

				adjustProgressWithXY = function(x, y) {
					/* within the bar, calculate angle of touch point */
					var pX, pY, angle, startTouchAngle, range, posValue;
					pX = x - cX;
					pY = y - cY;

					angle = Math.atan2(pY, pX);
					if (angle > Math.PI / 2.0) {
						angle -= (Math.PI * 2.0);
					}

					startTouchAngle = startAngle - (Math.PI * 2.0);
					range = endAngle - startAngle;
					posValue = (angle - startTouchAngle) / range;
					setProgress(posValue);

					if (params.onProgressUpdate) {
						/*
						 * use the progress value as this will have been clipped
						 * to the correct range [0..1] after the call to
						 * setProgress
						 */
						params.onProgressUpdate(progress);
					}
				};

				$(outerDiv).mousedown(function(e) {
					var offset, x, y, distance;
					offset = $(this).offset();
					x = e.pageX - offset.left;
					y = e.pageY - offset.top;

					distance = getDistance(x, y);

					if (distance > innerRadius && distance < radius) {
						mouseDown = true;
						adjustProgressWithXY(x, y);
					}
				}).mouseup(function() {
					mouseDown = false;
				}).mousemove(function(e) {
					var offset, x, y;
					if (mouseDown) {
						offset = $(outerDiv).offset();
						x = e.pageX - offset.left;
						y = e.pageY - offset.top;
						adjustProgressWithXY(x, y);
					}
				}).mouseleave(function() {
					mouseDown = false;
				});
			}());
		}
		return this;
	};
}(jQuery));
