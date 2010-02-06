package com.bean.gem;

import wox.serial.Easy;

public class leerXML {
	public class Student {
		private String name;
		private int registrationNumber;
		private Course[] courses;

		public Student(String name, int registrationNumber, Course[] courses) {
			super();
			this.name = name;
			this.registrationNumber = registrationNumber;
			this.courses = courses;
		}
	}

	public class Course {
		private int code;
		private String name;
		private int term;

		public Course(int code, String name, int term) {
			super();
			this.code = code;
			this.name = name;
			this.term = term;
		}

	}

	public Student student;
	public leerXML() {
		Course[] courses = {
				new Course(6756, "XML and Related Technologies", 2),
				new Course(9865, "Object Oriented Programming", 2),
				new Course(1134, "E-Commerce Programming", 3) };
		Student student = new Student("Carlos Jaimez", 76453, courses);
		String filename = "/student.xml";
		Easy.save(student, filename);
		
	}

}
