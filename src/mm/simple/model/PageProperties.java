package mm.simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PageProperties")
public class PageProperties {
String adress;
String wallpaper;
boolean custom;
long PageProperties_id;

public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getWallpaper() {
	return wallpaper;
}
public void setWallpaper(String wallpaper) {
	this.wallpaper = wallpaper;
}
public boolean isCustom() {
	return custom;
}
public void setCustom(boolean custom) {
	this.custom = custom;
}

@Id
@GeneratedValue
@Column(name = "PageProperties_id")
public long getPageProperties_id() {
	return PageProperties_id;
}
public void setPageProperties_id(long pageProperties_id) {
	PageProperties_id = pageProperties_id;
}

}
