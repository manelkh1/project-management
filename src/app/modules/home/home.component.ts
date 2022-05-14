import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Project } from 'src/app/Model/project';
import { ProjectService } from 'src/app/services/project-service/project.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from 'src/app/services/user-service/user.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { User } from 'src/app/Model/user';
import { ThisReceiver } from '@angular/compiler';
import { first } from 'rxjs';

export interface PeriodicElement {
  name: string;
  number: number;
  status: number;
  symbol: string;
}

// const ELEMENT_DATA: PeriodicElement[] = [
//   { number: 1, name: 'Hydrogen', status: 1.0079, symbol: 'H' },
//   { number: 2, name: 'Helium', status: 4.0026, symbol: 'He' },
//   { number: 3, name: 'Lithium', status: 6.941, symbol: 'Li' },
//   { number: 4, name: 'Beryllium', status: 9.0122, symbol: 'Be' },
//   { number: 5, name: 'Boron', status: 10.811, symbol: 'B' },
//   { number: 6, name: 'Carbon', status: 12.0107, symbol: 'C' },
//   { number: 7, name: 'Nitrogen', status: 14.0067, symbol: 'N' },
//   { number: 8, name: 'Oxygen', status: 15.9994, symbol: 'O' },
//   { number: 9, name: 'Fluorine', status: 18.9984, symbol: 'F' },
//   { number: 10, name: 'Neon', status: 20.1797, symbol: 'Ne' },
// ];

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = [
    'title',
    'description',
    'startDate',
    'endDate',
    'creationDate',
    'status',
  ];
  //dataSource : Liste vide initiale des sources de données
  dataSource!: MatTableDataSource<any>;
  //Attachez la pagination avec le matériau MatTableDataSource
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  form!: FormGroup;
  id!: number;
  projects!: MatTableDataSource<any>;
  objectUser: any;
  currentUser: User = new User();
  users: User[] = [];
  showAdmin!: boolean;
  loading = false;

  constructor(
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService,
    private userService: UserService,
    private _fb: FormBuilder
  ) {}

  // ************************************* currentuser**************************************//

  ngOnInit(): void {
    //jebna role mtee currentuser
    // this.objectUser =this.tokenStorage.getUser();
    // this.currentUser = this.objectUser.user;
    // console.log(this.currentUser)
    // this.role = this.currentUser.role;
    // Le routeur angulaire vous permet de récupérer facilement les paramètres de l'URL,
    //une fonctionnalité essentielle requise par la plupart des applications Web.
    this.id = this.route.snapshot.params['id'];

    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
    });
    this.getAllProjects();
    this.getProjects();
    this.getAllUsers();
  }

  
  //lorsque l'utilisateur saisit input et que la propriété de filtre MatTableDataSource
  //écoute la valeur input qui filtre les données dans dataSource
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getAllProjects() {
    this.projectService.getAllProjects().subscribe((data) => {
      //MatTableDataSource :  une source de données de table matTableDataSource intégrée.
      this.dataSource = new MatTableDataSource(data);
      //apply the pagination to all the dataSource
      this.dataSource.paginator = this.paginator;
      console.log(this.dataSource);
    });
  }

  getProjects() {
    this.userService.getProjectByUserId(this.id).subscribe((data) => {
      this.projects = new MatTableDataSource(data);
      this.projects.paginator = this.paginator;
      console.log(this.projects);
    });
  }

  subStrDescription(description: String) {
    if (description.length > 25) {
      return description.substring(0, 25) + '...';
    } else {
      return description.substring(0, 25);
    }
  }

  getAllUsers() {
    this.loading = true;
    this.userService
      .getAllUsers()
      .pipe(first())
      .subscribe((users) => {
        this.loading = false;
        this.users = users;
      });
    console.log(this.users);
  }

  // getUsers(){
  //   this.userService.getUserById(this.id).subscribe((data) =>{
  //     this.users = data;
  //     console.log(this.users);

  //   });

  // }

  getRoute(route: string, id: number) {
    this.router.navigate([route, id]);
  }

  subStrName(user: User) {
    return user.firstName.substring(0, 1) + user.lastName.substring(0, 1);
  }
}
