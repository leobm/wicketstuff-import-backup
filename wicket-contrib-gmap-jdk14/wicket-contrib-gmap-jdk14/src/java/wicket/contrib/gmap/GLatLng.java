/*
 * $Id: GPoint.java 577 2006-02-12 20:46:53Z syca $ $Revision: 577 $ $Date:
 * 2006-02-12 21:46:53 +0100 (Sun, 12 Feb 2006) $
 * 
 * ==================================================================== Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.gmap;

import java.io.Serializable;

/**
 * Represents Googles GLatLng class contains Geo location stored in latitude and longtitude
 * @author Nino Martinez Wael
 */
public class GLatLng implements Serializable
{

	private float longtitude;
	private float latitude;

	public GLatLng(float latitude,float longtitude)
	{
		this.longtitude = longtitude;
		this.latitude = latitude;
	}

	public float getLongtitude()
	{
		return longtitude;
	}

	public float getLatitude()
	{
		return latitude;
	}

	public String toString()
	{
		return "new GLatLng(" + latitude + ", " + longtitude + ")";
	}

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}

	public void setLongtitude(float longtitude)
	{
		this.longtitude = longtitude;
	}
}
