
//http://124.7.228.161/ncmsui/extjs/ux/DataView/DragSelector.js?_dc=3002

/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
 * @class Ext.ux.DataView.DragSelector
 * @extends Object
 * @author Ed Spencer
 * 
 */
Ext.define('Ext.ux.DataView.DragSelector', {
    requires: ['Ext.dd.DragTracker', 'Ext.util.Region'],

    /**
     * Initializes the plugin by setting up the drag tracker
     */
    init: function(dataview) {
        /**
         * @property dataview
         * @type Ext.view.View
         * The DataView bound to this instance
         */
        this.dataview = dataview;
        dataview.mon(dataview, {
            beforecontainerclick: this.cancelClick,
            scope: this,
            render: {
                fn: this.onRender,
                scope: this,
                single: true
            }
        });
    },

    /**
     * @private
     * Called when the attached DataView is rendered. This sets up the DragTracker instance that will be used
     * to created a dragged selection area
     */
    onRender: function() {
        /**
         * @property tracker
         * @type Ext.dd.DragTracker
         * The DragTracker attached to this instance. Note that the 4 on* functions are called in the scope of the 
         * DragTracker ('this' refers to the DragTracker inside those functions), so we pass a reference to the 
         * DragSelector so that we can call this class's functions.
         */
        this.tracker = Ext.create('Ext.dd.DragTracker', {
            dataview: this.dataview,
            el: this.dataview.el,
            dragSelector: this,
            onBeforeStart: this.onBeforeStart,
            onStart: this.onStart,
            onDrag : this.onDrag,
            onEnd  : this.onEnd
        });

        /**
         * @property dragRegion
         * @type Ext.util.Region
         * Represents the region currently dragged out by the user. This is used to figure out which dataview nodes are
         * in the selected area and to set the size of the Proxy element used to highlight the current drag area
         */
        this.dragRegion = Ext.create('Ext.util.Region');
    },

    /**
     * @private
     * Listener attached to the DragTracker's onBeforeStart event. Returns false if the drag didn't start within the
     * DataView's el
     */
    onBeforeStart: function(e) {
        return e.target == this.dataview.getEl().dom;
    },

    /**
     * @private
     * Listener attached to the DragTracker's onStart event. Cancel's the DataView's containerclick event from firing
     * and sets the start co-ordinates of the Proxy element. Clears any existing DataView selection
     * @param {EventObject} e The click event
     */
    onStart: function(e) {
        var dragSelector = this.dragSelector,
            dataview     = this.dataview;

        // Flag which controls whether the cancelClick method vetoes the processing of the DataView's containerclick event.
        // On IE (where else), this needs to remain set for a millisecond after mouseup because even though the mouse has
        // moved, the mouseup will still trigger a click event.
        this.dragging = true;

        //here we reset and show the selection proxy element and cache the regions each item in the dataview take up
        dragSelector.fillRegions();
        dragSelector.getProxy().show();
        dataview.getSelectionModel().deselectAll();
    },

    /**
     * @private
     * Reusable handler that's used to cancel the container click event when dragging on the dataview. See onStart for
     * details
     */
    cancelClick: function() {
        return !this.tracker.dragging;
    },

    /**
     * @private
     * Listener attached to the DragTracker's onDrag event. Figures out how large the drag selection area should be and
     * updates the proxy element's size to match. Then iterates over all of the rendered items and marks them selected
     * if the drag region touches them
     * @param {EventObject} e The drag event
     */
    onDrag: function(e) {
        var dragSelector = this.dragSelector,
            selModel     = dragSelector.dataview.getSelectionModel(),
            dragRegion   = dragSelector.dragRegion,
            bodyRegion   = dragSelector.bodyRegion,
            proxy        = dragSelector.getProxy(),
            regions      = dragSelector.regions,
            length       = regions.length,

            startXY   = this.startXY,
            currentXY = this.getXY(),
            minX      = Math.min(startXY[0], currentXY[0]),
            minY      = Math.min(startXY[1], currentXY[1]),
            width     = Math.abs(startXY[0] - currentXY[0]),
            height    = Math.abs(startXY[1] - currentXY[1]),
            region, selected, i;

        Ext.apply(dragRegion, {
            top: minY,
            left: minX,
            right: minX + width,
            bottom: minY + height
        });

        dragRegion.constrainTo(bodyRegion);
        proxy.setRegion(dragRegion);

        for (i = 0; i < length; i++) {
            region = regions[i];
            selected = dragRegion.intersect(region);

            if (selected) {
                selModel.select(i, true);
            } else {
                selModel.deselect(i);
            }
        }
    },

    /**
     * @private
     * Listener attached to the DragTracker's onEnd event. This is a delayed function which executes 1
     * millisecond after it has been called. This is because the dragging flag must remain active to cancel
     * the containerclick event which the mouseup event will trigger.
     * @param {EventObject} e The event object
     */
    onEnd: Ext.Function.createDelayed(function(e) {
        var dataview = this.dataview,
            selModel = dataview.getSelectionModel(),
            dragSelector = this.dragSelector;

        this.dragging = false;
        dragSelector.getProxy().hide();
    }, 1),

    /**
     * @private
     * Creates a Proxy element that will be used to highlight the drag selection region
     * @return {Ext.Element} The Proxy element
     */
    getProxy: function() {
        if (!this.proxy) {
            this.proxy = this.dataview.getEl().createChild({
                tag: 'div',
                cls: 'x-view-selector'
            });
        }
        return this.proxy;
    },

    /**
     * @private
     * Gets the region taken up by each rendered node in the DataView. We use these regions to figure out which nodes
     * to select based on the selector region the user has dragged out
     */
    fillRegions: function() {
        var dataview = this.dataview,
            regions  = this.regions = [];

        dataview.all.each(function(node) {
            regions.push(node.getRegion());
        });
        this.bodyRegion = dataview.getEl().getRegion();
    }
});


//http://124.7.228.161/ncmsui/extjs/ux/DataView/LabelEditor.js?_dc=3002

/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
 * @class Ext.ux.DataView.LabelEditor
 * @extends Ext.Editor
 */
Ext.define('Ext.ux.DataView.LabelEditor', {

    extend: 'Ext.Editor',

    alignment: 'tl-tl',

    completeOnEnter: true,

    cancelOnEsc: true,

    shim: false,

    autoSize: {
        width: 'boundEl',
        height: 'field'
    },

    labelSelector: 'x-editable',

    requires: [
        'Ext.form.field.Text'
    ],

    constructor: function(config) {
        config.field = config.field || Ext.create('Ext.form.field.Text', {
            allowBlank: false,
            selectOnFocus:true
        });
        this.callParent([config]);
    },

    init: function(view) {
        this.view = view;
        this.mon(view, 'render', this.bindEvents, this);
        this.on('complete', this.onSave, this);
    },

    // initialize events
    bindEvents: function() {
        this.mon(this.view.getEl(), {
            click: {
                fn: this.onClick,
                scope: this
            }
        });
    },

    // on mousedown show editor
    onClick: function(e, target) {
        var me = this,
            item, record;

        if (Ext.fly(target).hasCls(me.labelSelector) && !me.editing && !e.ctrlKey && !e.shiftKey) {
            e.stopEvent();
            item = me.view.findItemByChild(target);
            record = me.view.store.getAt(me.view.indexOf(item));
            me.startEdit(target, record.data[me.dataIndex]);
            me.activeRecord = record;
        } else if (me.editing) {
            me.field.blur();
            e.preventDefault();
        }
    },

    // update record
    onSave: function(ed, value) {
        this.activeRecord.set(this.dataIndex, value);
    }
});


//http://124.7.228.161/ncmsui/extjs/ux/TimePickerField.js?_dc=3002

Ext.define('Ext.ux.TimePickerField', {
	  extend: 'Ext.form.field.Base',
	  alias: 'widget.timepicker',
	  alternateClassName: 'Ext.form.field.TimePickerField',
	  requires: ['Ext.form.field.Number'],
	  
	  inputType: 'hidden',
	  style: 'padding:4px 0 0 0;margin-bottom:0px',
	  
	  hoursSpinner: null,
	  minutesSpinner: null,
	  secondsSpinner: null,
	  
	  spinnerCfg: {
		  width: 40
	  },
	  
	  initComponent: function() {
		  var cfg = Ext.apply({}, this.spinnerCfg, {
			    readOnly: this.readOnly,
			    disabled: this.disabled,
			    style: 'float: left',
			    listeners: {
				    change: {
					    fn: this.onSpinnerChange,
					    scope: this
				    }
			    }
		    });
		  
		  this.spinners = [];
		  
		  this.hoursSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
			      minValue: 0,
			      maxValue: 23
		      }));
		  this.minutesSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
			      minValue: 0,
			      maxValue: 59
		      }));
		  // TODO ä½¿ç”¨timeformat åˆ¤æ–­æ˜¯å¦åˆ›å»ºç§’è¾“å…¥æ¡†,  may be second field is no need.
		  this.secondsSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
			      minValue: 0,
			      maxValue: 59
		      }));
		  
		  this.spinners.push(this.hoursSpinner);
		  this.spinners.push(this.minutesSpinner);
		  this.spinners.push(this.secondsSpinner);
		  this.callParent();
	  },
	  
	  onRender: function() {
		  // å–å¾—åˆå§‹å€¼, æ·»åŠ ä¸‰ä¸ªæ•´æ•°è¾“å…¥æ¡†.  Deal whit init Value
		  this.value = this.value || Ext.Date.format(new Date(), 'H:i:s');
		  this.rawValue = this._valueSplit(this.value);
		  
		  this.callParent(arguments);
		  // render  to  original BaseField input div. 
		  var spinnerWrap = Ext.get(Ext.DomQuery.selectNode('div', this.el.dom));
		  this.callSpinnersFunction('render', spinnerWrap);
		  
		  Ext.core.DomHelper.append(spinnerWrap, {
			    tag: 'div',
			    cls: 'x-form-clear-left'
		    });
	  },
	  _valueSplit: function(v) {
		  var split = v.split(':');
		  return {
			  h: split.length > 0 ? split[0] : 0,
			  m: split.length > 1 ? split[1] : 0,
			  s: split.length > 2 ? split[2] : 0
		  };
	  },
	  onSpinnerChange: function() {
		  if (!this.rendered) {
			  return;
		  }
		  this.fireEvent('change', this, this.getValue(), this.getRawValue());
	  },
	  // ä¾æ¬¡è°ƒç”¨å„è¾“å…¥æ¡†å‡½æ•°
	  callSpinnersFunction: function(funName, args) {
		  for (var i = 0; i < this.spinners.length; i++) {
			  this.spinners[i][funName](args);
		  }
	  },
	  disable: function() {
		  this.callParent(arguments);
		  this.callSpinnersFunction('disable', arguments);
	  },
	  enable: function() {
		  this.callParent(arguments);
		  this.callSpinnersFunction('enable', arguments);
	  },
	  setReadOnly: function() {
		  this.callParent(arguments);
		  this.callSpinnersFunction('setReadOnly', arguments);
	  },
	  clearInvalid: function() {
		  this.callParent(arguments);
		  this.callSpinnersFunction('clearInvalid', arguments);
	  },
	  getRawValue: function() {
		  if (!this.rendered) {
			  this.date = new Date();
			  return {
				  h: this.date.getHours(),
				  m: this.date.getMinutes(),
				  s: this.date.getSeconds()
			  };
		  } else {
			  return {
				  h: this.hoursSpinner.getValue(),
				  m: this.minutesSpinner.getValue(),
				  s: this.secondsSpinner.getValue()
			  };
		  }
	  },
	  setRawValue: function(values) {
		  if (this.hoursSpinner) {
			  this.hoursSpinner.setValue(values.h);
			  this.minutesSpinner.setValue(values.m);
			  this.secondsSpinner.setValue(values.s);
		  }
	  },
	  isValid: function(preventMark) {
		  return this.hoursSpinner.isValid(preventMark) && this.minutesSpinner.isValid(preventMark)
		    && this.secondsSpinner.isValid(preventMark);
	  },
	  validate: function() {
		  return this.hoursSpinner.validate() && this.minutesSpinner.validate()
		    && this.secondsSpinner.validate();
	  },
	  getValue: function() {
		  var v = this.getRawValue();
		  return Ext.String.leftPad(v.h, 2, '0') + ':' + Ext.String.leftPad(v.m, 2, '0') + ':'
		    + Ext.String.leftPad(v.s, 2, '0');
	  },
	  setValue: function(value) {
		  if (!this.rendered) {
			  this.value = value;
			  return;
		  }
		  value = this._valueSplit(value);
		  this.setRawValue(value);
		  this.validate();
	  }
  });



//http://124.7.228.161/ncmsui/extjs/ux/TreeEditing.js?_dc=3002

/**
 * <p>
 * Provides edit capabilities for a tree node.
 * </p>
 * <pre><code>
   var tree = new Ext.tree.Panel({
      plugins:[{
         pluginId: 'edit-plug'
         ,ptype: 'treeediting'
      }]
      ...
   });
 * </code></pre>
 * @class Ext.ux.tree.TreeEditing
 * @extends Ext.grid.plugin.CellEditing
 * @license Licensed under the terms of the Open Source <a href="http://www.gnu.org/licenses/lgpl.html">LGPL 3.0 license</a>.  Commercial use is permitted to the extent that the code/component(s) do NOT become part of another Open Source or Commercially licensed development library or toolkit without explicit permission.
 *  
 * @version 0.1 (June 22, 2011)
 * @constructor
 * @param {Object} config 
 */
Ext.define('Ext.ux.tree.TreeEditing', {
    alias: 'plugin.treeediting'
    ,extend: 'Ext.grid.plugin.CellEditing'
    
    
    /**
     * @override
     * @private Collects all information necessary for any subclasses to perform their editing functions.
     * @param record
     * @param columnHeader
     * @returns {Object} The editing context based upon the passed record and column
     */
    ,getEditingContext: function(record, columnHeader) {
        var me = this,
            grid = me.grid,
            store = grid.store,
            rowIdx,
            colIdx,
            view = grid.getView(),
            root = grid.getRootNode(),
            value;

        // If they'd passed numeric row, column indices, look them up.
        if (Ext.isNumber(record)) {
            rowIdx = record;
            record = root.getChildAt(rowIdx);
        } else {
            rowIdx = root.indexOf(record);
        }
        if (Ext.isNumber(columnHeader)) {
            colIdx = columnHeader;
            columnHeader = grid.headerCt.getHeaderAtIndex(colIdx);
        } else {
            colIdx = columnHeader.getIndex();
        }

        value = record.get(columnHeader.dataIndex);
        return {
            grid: grid,
            record: record,
            field: columnHeader.dataIndex,
            value: value,
            row: view.getNode(rowIdx),
            column: columnHeader,
            rowIdx: rowIdx,
            colIdx: colIdx
        };
    }

});//eo class

//end of file



//http://124.7.228.161/ncmsui/extjs/ux/DateTimePicker.js?_dc=3002


Ext.define('Ext.ux.DateTimePicker', {
	  extend: 'Ext.picker.Date',
	  alias: 'widget.datetimepicker',
	  timeFormat: 'H:i:s',
	  timeLabel: '<b>Time</b>',
	  requires: ['Ext.ux.TimePickerField'],
	  
	  initComponent: function() {
		  // keep time part for value
		  var value = this.value || new Date();
		  this.callParent();
		  this.value = value;
	  },
	  onRender: function(container, position) {
		  if (!this.timefield) {
			  this.timefield = Ext.create('Ext.ux.TimePickerField', {
				    fieldLabel: this.timeLabel,
				    labelWidth: 40,
				    value: Ext.Date.format(this.value, this.timeFormat)
			    });
		  }
		  this.timefield.ownerCt = this;
		  this.timefield.on('change', this.timeChange, this);
		  this.callParent(arguments);
		  
		  var table = Ext.get(Ext.DomQuery.selectNode('table', this.el.dom));
		  var tfEl = Ext.core.DomHelper.insertAfter(table, {
			    tag: 'div',
			    style: 'border:0px;',
			    children: [{
				      tag: 'div',
				      cls: 'x-datepicker-footer ux-timefield'
			      }]
		    }, true);
		  this.timefield.render(this.el.child('div div.ux-timefield'));
		  
		  var p = this.getEl().parent('div.x-layer');
		  if (p) {
			  p.setStyle("height", p.getHeight() + 31);
		  }
	  },
	  // listener 时间域修改,  timefield change
	  timeChange: function(tf, time, rawtime) {
		  if (this.todayKeyListener) { // after initEvent
			  this.setValue(this.value);
		  } else {
			  this.value = this.getDateTime(this.value);
		  }
	  },
	  getDateTime: function(value) {
		  if (this.timefield) {
			  var rawtime = this.timefield.getRawValue();
			  value.setHours(rawtime.h);
			  value.setMinutes(rawtime.m);
			  value.setSeconds(rawtime.s);
		  }
		  return value;
	  },
	  // overwrite
	  setValue: function(value) {
		  this.value = value;
		  this.value = this.getDateTime(this.value);
		  return this.update(this.value);
	  },
	  // overwrite
	  getValue: function() {
		  return this.getDateTime(this.value);
	  }
  });



// http://124.7.228.161/ncmsui/extjs/ux/DateTimeField.js?_dc=3002


Ext.define('Ext.ux.DateTimeField', {
	  extend: 'Ext.form.field.Date',
	  alias: 'widget.datetimefield',
	  dateFormat: 'Y-m-d',
	  timeFormat: 'H:i:s',
	  requires: ['Ext.ux.DateTimePicker'],
	  
	  initComponent: function() {
		  this.callParent();
		  this.format = this.dateFormat + ' ' + this.timeFormat;
	  },
	  // overwrite
	  createPicker: function() {
		  var me = this, format = Ext.String.format;
		  
		  return Ext.create('Ext.ux.DateTimePicker', {
			    ownerCt: me.ownerCt,
			    renderTo: document.body,
			    floating: true,
			    hidden: true,
			    focusOnShow: true,
			    minDate: me.minValue,
			    maxDate: me.maxValue,
			    disabledDatesRE: me.disabledDatesRE,
			    disabledDatesText: me.disabledDatesText,
			    disabledDays: me.disabledDays,
			    disabledDaysText: me.disabledDaysText,
			    format: me.format,
			    timeFormat: this.timeFormat,
			    dateFormat: this.dateFormat,
			    showToday: me.showToday,
			    startDay: me.startDay,
			    minText: format(me.minText, me.formatDate(me.minValue)),
			    maxText: format(me.maxText, me.formatDate(me.maxValue)),
			    listeners: {
				    scope: me,
				    select: me.onSelect
			    },
			    keyNavConfig: {
				    esc: function() {
					    me.collapse();
				    }
			    }
		    });
	  }
  });


//http://124.7.228.161/ncmsui/extjs/js/writergrid.js?_dc=3002

Ext.define('Writer.Grid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.writergrid',

    requires: [
        'Ext.grid.plugin.CellEditing',
        'Ext.form.field.Text',
        'Ext.toolbar.TextItem'
    ],

    initComponent: function(){

       // this.editing = Ext.create('Ext.grid.plugin.CellEditing');

        Ext.apply(this, {
	    forceFit:'true',
            iconCls: 'icon-grid',
            frame: true,
	    preventHeader: true,
	    toFrontOnShow:true,
            columnLines:'true',
	    hideHeaders : false,
	    headerPosition:'top',
	    frame:true,
            autoScroll:'true',
            // plugins: [this.editing],
            // paging bar on the bottom
            bbar: Ext.create('Ext.PagingToolbar', {
		    store: this.store,
		    displayInfo: true,
		    displayMsg: 'Displaying records {0} - {1} of {2}',
		    emptyMsg: "No records to display"//,
		    /*items:[
		        '-', {
		        text: 'Show Preview',
		        pressed: pluginExpanded,
		        enableToggle: true,
		        toggleHandler: function(btn, pressed) {
		            var preview = Ext.getCmp('gv').getPlugin('preview');
		            preview.toggleExpanded(pressed);
		        }
		    }]*/
		})

            /*dockedItems: [{
                xtype: 'toolbar',
                items: [{
                    iconCls: 'icon-add',
                    text: 'Add',
                    scope: this,
                    handler: this.onAddClick
                }, {
                    iconCls: 'icon-delete',
                    text: 'Delete',
                    disabled: true,
                    itemId: 'delete',
                    scope: this,
                    handler: this.onDeleteClick
                }]
            }]*/
        });
        this.callParent();
        //this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
	this.doLayout();
    }
});

// http://124.7.228.161/ncmsui/extjs/js/commons.js?_dc=3002

var entityName = "";
var mainStoregrid = "";

Array.prototype.getUnique = function () {
	var o = new Object();
	var i, e;
	for (i = 0; e = this[i]; i++) {o[e] = 1};
	var a = new Array();
	for (e in o) {a.push (e)};
	return a;
}

Array.prototype.inArray = function(value){
  var i;
  for(i=0; i < this.length; i++){
    if(this[i] === value)
      return true;
  };
  return false;
};

Date.prototype.format = function(format) {
	var returnStr = '';
	var replace = Date.replaceChars;
	for (var i = 0; i < format.length; i++) {
		var curChar = format.charAt(i);
		if (replace[curChar]) {
			returnStr += replace[curChar].call(this);
		} else {
			returnStr += curChar;
		}
	}
	return returnStr;
};

Date.replaceChars = {
	shortMonths: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
	longMonths: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
	shortDays: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
	longDays: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
	
	// Day
	d: function() { return (this.getDate() < 10 ? '0' : '') + this.getDate(); },
	D: function() { return Date.replaceChars.shortDays[this.getDay()]; },
	j: function() { return this.getDate(); },
	l: function() { return Date.replaceChars.longDays[this.getDay()]; },
	N: function() { return this.getDay() + 1; },
	S: function() { return (this.getDate() % 10 == 1 && this.getDate() != 11 ? 'st' : (this.getDate() % 10 == 2 && this.getDate() != 12 ? 'nd' : (this.getDate() % 10 == 3 && this.getDate() != 13 ? 'rd' : 'th'))); },
	w: function() { return this.getDay(); },
	z: function() { return "Not Yet Supported"; },
	// Week
	W: function() { return "Not Yet Supported"; },
	// Month
	F: function() { return Date.replaceChars.longMonths[this.getMonth()]; },
	m: function() { return (this.getMonth() < 9 ? '0' : '') + (this.getMonth() + 1); },
	M: function() { return Date.replaceChars.shortMonths[this.getMonth()]; },
	n: function() { return this.getMonth() + 1; },
	t: function() { return "Not Yet Supported"; },
	// Year
	L: function() { return "Not Yet Supported"; },
	o: function() { return "Not Supported"; },
	Y: function() { return this.getFullYear(); },
	y: function() { return ('' + this.getFullYear()).substr(2); },
	// Time
	a: function() { return this.getHours() < 12 ? 'am' : 'pm'; },
	A: function() { return this.getHours() < 12 ? 'AM' : 'PM'; },
	B: function() { return "Not Yet Supported"; },
	g: function() { return this.getHours() % 12 || 12; },
	G: function() { return this.getHours(); },
	h: function() { return ((this.getHours() % 12 || 12) < 10 ? '0' : '') + (this.getHours() % 12 || 12); },
	H: function() { return (this.getHours() < 10 ? '0' : '') + this.getHours(); },
	i: function() { return (this.getMinutes() < 10 ? '0' : '') + this.getMinutes(); },
	s: function() { return (this.getSeconds() < 10 ? '0' : '') + this.getSeconds(); },
	// Timezone
	e: function() { return "Not Yet Supported"; },
	I: function() { return "Not Supported"; },
	O: function() { return (this.getTimezoneOffset() < 0 ? '-' : '+') + (this.getTimezoneOffset() / 60 < 10 ? '0' : '') + (this.getTimezoneOffset() / 60) + '00'; },
	T: function() { return "T"; },//return "Not Yet Supported"; },
	Z: function() { return "Z"; },//return this.getTimezoneOffset() * 60; },
	// Full Date/Time
	c: function() { return "Not Yet Supported"; },
	r: function() { return this.toString(); },
	U: function() { return this.getTime() / 1000; }
};

Ext.define('Ext.ux.ColorField', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.colorfield',    
    requires: ['Ext.form.field.VTypes', 'Ext.layout.component.field.Text'],

    lengthText: "Color hex values must be either 3 or 6 characters including #.",
    blankText: "Must have a hexidecimal value in the format ABCDEF.",
    
    regex: /^[0-9a-f#]{4,7}$/i,

     validateValue : function(value){
        if (this.allowBlank && !value)
        {
            return true
        }
        if(!this.getEl()) {
            return true;
        }
        if(value.length!=4 && value.length!=7) {
            this.markInvalid(Ext.String.format(this.lengthText, value));
            return false;
        }
        if((value.length < 1 && !this.allowBlank) || !this.regex.test(value)) {
            this.markInvalid(Ext.String.format(this.blankText, value));
            return false;
        }


        return true;
    },
    markInvalid : function( msg ) {
        Ext.ux.ColorField.superclass.markInvalid.call(this, msg);
        this.inputEl.setStyle({
            'background-image': 'url(../resources/themes/images/default/grid/invalid_line.gif)'
        });
    },
    
    setValue : function(hex){
        Ext.ux.ColorField.superclass.setValue.call(this, hex);
        //this.setColor(hex);
    },
    
    setColor : function(hex) {
        Ext.ux.ColorField.superclass.setFieldStyle.call(this, {
            'background-color': '#'+hex,
            'background-image': 'none'
        });
    },

    menuListeners : {
        select: function(m, d){
            this.setValue('#'+d);
        },
        show : function(){
            this.onFocus();
        },
        hide : function(){
            this.focus();
            var ml = this.menuListeners;
            this.menu.un("select", ml.select,  this);
            this.menu.un("show", ml.show,  this);
            this.menu.un("hide", ml.hide,  this);
        }
    },
    
    onTriggerClick : function(e){
        if(this.disabled){
            return;
        }
        
        this.menu = new Ext.menu.ColorPicker({
            shadow: true,
            autoShow : true
        });
        this.menu.alignTo(this.inputEl, 'tl-bl?');
        this.menu.doLayout();
        
        this.menu.on(Ext.apply({}, this.menuListeners, {
            scope:this
        }));
        
        this.menu.show(this.inputEl);
    }
});



