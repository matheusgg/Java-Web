package datatable;

import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

public class CustomDataModelListener implements DataModelListener {

	@Override
	public void rowSelected(DataModelEvent event) {
		System.out.println(event.getRowIndex() + " - " + event.getRowData());
	}

}
