package tp.model.organization;

import tp.model.items.Item;
import tp.model.utils.WithId;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Organization implements WithId {

	String id;

	String name;
	String email;

	List<BranchOffice> branchOffices;
	List<Item> items;

	public void listItems() {
		this.items.forEach(item -> System.out.println(item.getName()));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Organization :: ").append(name).append("\n")
				.append("Email :: ").append(email).append("\n");

		return builder.toString();
	}
}
