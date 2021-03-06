/*
 * 	This file is part of DicomFlow.
 * 
 * 	DicomFlow is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 * 
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 * 
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package br.ufpb.dicomflow.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ufpb.dicomflow.service.ServiceException;
import br.ufpb.dicomflow.service.ServiceLocator;


@Entity
@Table(name="request_service")
public class RequestService extends AbstractPersistence {

	
	//type values
	public static final String SENT = "sent";
	public static final String RECEIVED = "received";
	
	//status values
	public static final String OPEN = "open";
	public static final String PENDING = "pending";
	public static final String CLOSED = "closed";
	public static final String ERROR = "error";
	public static final String LOCK = "lock";
	
	//action values
	public static final String PUT = "put";
	public static final String RESULT = "result";

	/**
	 * 
	 */
	private static final long serialVersionUID = -4338082442529426759L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",unique=true)
	private Long id;
	
	private String link;
	
	@Column(name="study_iuid")
	private String studyIuid;
	
	@Column(name="study_modality")
	private String studyModality;
	
	@Column(name="study_description")
	private String studyDescription;
	
	@Column(name="patient_id")
	private String patientID;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="patient_gender")
	private String patientGender;
	
	@Column(name="patient_birth")
	private String patientBirth;
	
	@Transient
	private byte[] bytes;
	
	@Column
	private String accessMail;
	
	@Column
	private String filename;
	
	private String messageID;
	
	private String host;
	
	private int port;
	
	private String type;
	
	private String status;
	
	private String action;
	
	private int downloadAttempt;
	
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_request_service")
	private Set<RequestServiceAccess> requestServiceAccesses;
	
	public RequestService(){
		
	}
	
	public RequestService(String link){
		this.link = link;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set<RequestServiceAccess> getRequestServiceAccesses() {
		return requestServiceAccesses;
	}

	public void setRequestServiceAccesses(Set<RequestServiceAccess> requestServiceAccesses) {
		this.requestServiceAccesses = requestServiceAccesses;
	}

	public String getStudyIuid() {
		return studyIuid;
	}

	public void setStudyIuid(String studyIuid) {
		this.studyIuid = studyIuid;
	}
	
	public String getStudyModality() {
		return studyModality;
	}

	public void setStudyModality(String studyModality) {
		this.studyModality = studyModality;
	}

	public String getStudyDescription() {
		return studyDescription;
	}

	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientBirth() {
		return patientBirth;
	}

	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDownloadAttempt() {
		return downloadAttempt;
	}

	public void setDownloadAttempt(int downloadAttempt) {
		this.downloadAttempt = downloadAttempt;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAccessMail() {
		return accessMail;
	}

	public void setAccessMail(String accessMail) {
		this.accessMail = accessMail;
	}

	@Override
	public void save() throws ServiceException{
		ServiceLocator.singleton().getPersistentService().saveOrUpdate(this);
	}
	
	@Override
	public void remove() throws ServiceException {
		ServiceLocator.singleton().getPersistentService().remove(this);
		
	}


}
