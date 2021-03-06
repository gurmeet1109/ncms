Ext.namespace("dnd");

dnd.SortableDataView = function(config) {
	Ext.apply(this, config || {}, {
		dragCls : 'x-view-sortable-drag',
		viewDragCls : 'x-view-sortable-dragging'
	});
	dnd.SortableDataView.superclass.constructor.call(this);
};

Ext.extend(dnd.SortableDataView, Ext.util.Observable, {

	events : {
		'drop' : true
	},

	init : function(view) {
		window.sdv = this;
		this.view = view;
		view.on('render', this.onRender, this);
	},

	onRender : function() {

		var self        = this,
		    v           = this.view,
		    ds          = v.store,
		    dd          = new Ext.dd.DragDrop(v.el),
		    dragCls     = this.dragCls
		    viewDragCls = this.viewDragCls;

		// onMouseDown : if found an element, record it for future startDrag
		dd.onMouseDown = function(e) {

			var t,idx,record;
			this.dragData = null;

			try {
				t = e.getTarget(v.itemSelector);
				idx = v.indexOf(t);
				record = ds.getAt(idx);

				// Found a record to move
				if (t && record) {
					this.dragData = {
						origIdx : idx,
						lastIdx : idx,
						record  : record
					};
					return true;
				}
			} catch (ex) { this.dragData = null; }
			return false;
		};

		// startDrag: add dragCls to the element
		dd.startDrag = function(x, y) {
			if (!this.dragData) { return false; }
			Ext.fly(v.getNode(this.dragData.origIdx)).addCls(dragCls);
			v.el.addCls(viewDragCls);
		};

		// endDrag : remove dragCls and fire "drop" event
		dd.endDrag = function(e) {
			if (!this.dragData) { return true; }
			Ext.fly(v.getNode(this.dragData.lastIdx)).removeCls(dragCls);
			v.el.removeCls(viewDragCls);
			self.fireEvent('drop', this.dragData.origIdx,
				this.dragData.lastIdx, this.dragData.record);
			return true;
		};

		// onDrag : if correct position, move record
		dd.onDrag = function(e) {

			var t,idx,record,data = this.dragData;
			if (!data) { return false; }

			try {
				t = e.getTarget(v.itemSelector);
				idx = v.indexOf(t);
				record = ds.getAt(idx);

				if (idx === data.lastIdx) { return true; }

				// found new position : move record and re-add dragCls
				if (t && record) {
					data.lastIdx = idx;
					ds.remove(data.record);
					ds.insert(idx, [data.record]);
					Ext.fly(v.getNode(idx)).addCls(dragCls);
					return true;
				}
			} catch (ex) { return false; }
			return false;
		};

		this.dd = dd;

	}
});
