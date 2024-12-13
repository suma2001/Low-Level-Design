package MeetingRoomScheduler;

/*
Meeting Room Scheduler
1) N meeting rooms with a given capacity
2) Book a meeting room for a given start time, end time and num_participants
    2.1 User can see list of all unoccupied meeting rooms for a given timeslot and capacity
3) Booking Strategy First-Come First-Serve basis
4) Send notification to all the participants of the meeting
5) History of all meetings scheduled
6) Check participants calendar for availability
7) Calendar to track bookings and meetings
8) Update meeting/Cancel meeting
    8.1 Update meeting participants - add participant (if cap crosses?)

Entities: Meeting, MeetingRoom, Participant, TimeSlot, Calendar, MeetingRoomController
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

interface Observer {
    public void update(String message);
}
class Participant implements Observer {
    String name;
    String email;
    String mobile;
    Calendar calendar;

    public Participant(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.calendar = new Calendar();
    }

    @Override
    public void update(String message) {
        System.out.println(STR."Email sent to \{this.email}: \{message}");
    }
}

class Meeting {
    String meetingId;
    String title;
    List<Participant> participants;
    TimeSlot timeSlot;

    public Meeting(String meetingId, String title, List<Participant> participants, TimeSlot timeSlot) {
        this.meetingId = meetingId;
        this.title = title;
        this.participants = participants;
        this.timeSlot = timeSlot;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        this.participants.remove(participant);
    }
    public void notifyParticipants(String message) {
        for(Participant participant: participants) {
            participant.update(message);
        }
    }
}

class MeetingRoom {
    String roomId;
    Meeting meeting;
    int capacity;
    Calendar calendar;
    Location location;

    public MeetingRoom(String roomId, Location location, int capacity) {
        this.roomId = STR."\{roomId}_\{location.floorId}_\{location.buildingId}";
        this.capacity = capacity;
        this.location = location;
        this.calendar = new Calendar();
    }

    public boolean isAvailable(TimeSlot slot) {
        return this.calendar.isAvailable(slot);
    }
}

class TimeSlot {
    int startTime;
    int endTime;


    public TimeSlot(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isOverlapping(TimeSlot slot, TimeSlot newSlot) {
        if((slot.startTime <= newSlot.startTime && slot.endTime <= newSlot.startTime) ||
                (slot.startTime >= newSlot.endTime && slot.endTime >= newSlot.endTime)) {
            return true;
        }
        return false;
    }
}

class Location {
    int floorId;
    int buildingId;

    public Location(int floorId, int buildingId) {
        this.floorId = floorId;
        this.buildingId = buildingId;
    }
}

class Calendar {
    List<TimeSlot> bookings;

    public Calendar() {
        this.bookings = new ArrayList<>();
    }

    public void addTimeSlot(TimeSlot slot) {
        this.bookings.add(slot);
    }

    public void removeTimeSlot(TimeSlot slot) {
        this.bookings.remove(slot);
    }

    public boolean isAvailable(TimeSlot slot) {
        if(this.bookings.size() == 0) {
            return true;
        }
        for(TimeSlot booking: this.bookings) {
            if(slot.isOverlapping(slot, booking)) {
                return true;
            }
        }
        return false;
    }
}

interface RoomSelectionStrategy {
    public MeetingRoom selectRoom(List<MeetingRoom> meetingRooms);
}

class FirstComeFirstServeStrategy implements RoomSelectionStrategy {
    @Override
    public MeetingRoom selectRoom(List<MeetingRoom> availableRooms) {
        if(!availableRooms.isEmpty()) {
            return availableRooms.getFirst();
        }
        return null;
    }
}
class MeetingRoomController {
    List<MeetingRoom> meetingRooms = new ArrayList<>();

    public void addMeetingRoom(MeetingRoom room) {
        this.meetingRooms.add(room);
        System.out.println("Add Rooms: " + Arrays.toString(this.meetingRooms.toArray()));
    }

    public List<MeetingRoom> getAvailableMeetingRooms(int startTime, int endTime, int numParticipants) {
        List<MeetingRoom> availableRooms = new ArrayList<>();
        for(MeetingRoom room: this.meetingRooms) {
            System.out.println("Room: " + room + " " + room.capacity + " " + numParticipants);
            if(room.capacity >= numParticipants && room.isAvailable(new TimeSlot(startTime, endTime))) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

class MeetingScheduler {
    RoomSelectionStrategy selectionStrategy;
    MeetingRoomController roomController;
    public MeetingScheduler(RoomSelectionStrategy selectionStrategy, MeetingRoomController roomController) {
        this.roomController = roomController;
        this.selectionStrategy = selectionStrategy;
    }

    public MeetingRoom scheduleMeeting(String meetingId, String title, List<Participant> participants, TimeSlot timeSlot) {
        List<MeetingRoom> availableRooms = roomController.getAvailableMeetingRooms(timeSlot.startTime, timeSlot.endTime, participants.size());
        System.out.println("Available Rooms: " + Arrays.toString(availableRooms.toArray()));
        MeetingRoom selectedRoom = selectionStrategy.selectRoom(availableRooms);

        if(selectedRoom != null) {
            selectedRoom.meeting = new Meeting(meetingId, title, participants, timeSlot);
            selectedRoom.calendar.addTimeSlot(timeSlot);
            selectedRoom.meeting.notifyParticipants(STR."Meeting scheduled in room \{selectedRoom.roomId} from \{timeSlot.startTime} - \{timeSlot.endTime}");
            System.out.println(STR."Meeting scheduled in room \{selectedRoom.roomId} from \{timeSlot.startTime} - \{timeSlot.endTime}");
            return selectedRoom;
        } else {
            System.out.println(STR."No room available for given time slot and capacity");
            return null;
        }
    }
}

class MeetingApplication {
    public static void main(String[] args) {

        MeetingRoom room1 = new MeetingRoom("Room1", new Location(1,101), 3);
        MeetingRoom room2 = new MeetingRoom("Room2", new Location(1,101), 2);
        MeetingRoom room3 = new MeetingRoom("Room3", new Location(1,101), 5);

        MeetingRoomController controller = new MeetingRoomController();

        // Add meeting rooms
        controller.addMeetingRoom(room1);
        controller.addMeetingRoom(room2);
        controller.addMeetingRoom(room3);

        // Create participants
        Participant p1 = new Participant("Shreya", "abc@gmail.com", "+91234567890");
        Participant p2 = new Participant("Shravani", "xyz@gmail.com", "+91234567890");

        List<Participant> participants = new ArrayList<>();
        participants.add(p1);
        participants.add(p2);

        // Create a meeting
        MeetingScheduler scheduler = new MeetingScheduler(new FirstComeFirstServeStrategy(), controller);
        scheduler.scheduleMeeting("M1", "Archiving Discussion", participants, new TimeSlot(2, 3));
        scheduler.scheduleMeeting("M2", "Workflow Discussion", participants, new TimeSlot(2, 3));
    }
}