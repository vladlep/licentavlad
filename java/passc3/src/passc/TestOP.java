package passc;

import OpenCOM.*;

public class TestOP {

    /** Creates a new instance of TestProgram */
    public TestOP() {
    }

	
 /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

    	System.out.println("aici");
        // Create the OpenCOM runtime & Get the IOpenCOM interface reference
        OpenCOM runtime = new OpenCOM();
        IOpenCOM pIOCM = (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");

        
        // Create the NbSource 1 component - variable A
        IUnknown pNbSourceIUnk = (IUnknown) pIOCM.createInstance("passc.NbSource", "A");
        ILifeCycle pILife = (ILifeCycle) pNbSourceIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the NbSource 2 component - variable B
        IUnknown pNbSourceIUnk2 = (IUnknown) pIOCM.createInstance("passc.NbSource", "B");
        pILife = (ILifeCycle) pNbSourceIUnk2.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the NbSource 3 component - variable C
        IUnknown pNbSourceIUnk3 = (IUnknown) pIOCM.createInstance("passc.NbSource", "C");
        pILife = (ILifeCycle) pNbSourceIUnk3.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the NbSource 4 component - variable D
        IUnknown pNbSourceIUnk4 = (IUnknown) pIOCM.createInstance("passc.NbSource", "D");
        pILife = (ILifeCycle) pNbSourceIUnk4.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // A-(A+B)*(C/D)

        // Create the AddOp component
        IUnknown pAddOpIUnk = (IUnknown) pIOCM.createInstance("passc.AddOp", "Add");
        pILife = (ILifeCycle) pAddOpIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the SubOp component
        IUnknown pSubOpIUnk = (IUnknown) pIOCM.createInstance("passc.SubOp", "Sub");
        pILife = (ILifeCycle) pSubOpIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);

        // Create the MulOp component
        IUnknown pMulOpIUnk = (IUnknown) pIOCM.createInstance("passc.MulOp", "Mul");
        pILife = (ILifeCycle) pMulOpIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the DivOp component
        IUnknown pDivOpIUnk = (IUnknown) pIOCM.createInstance("passc.DivOp", "Div");
        pILife = (ILifeCycle) pDivOpIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);


        // Create the Monitor component
        IUnknown pMonitorIUnk = (IUnknown) pIOCM.createInstance("passc.Monitor", "Monitor");
        pILife = (ILifeCycle) pNbSourceIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);

        System.out.println("Created components");


        // create connections for A+B
        long ConnID1 = runtime.connect(pAddOpIUnk, pNbSourceIUnk, "passc.INb");
        long ConnID2 = runtime.connect(pAddOpIUnk, pNbSourceIUnk2, "passc.INb");

        // create connections for C/D
        long ConnID3 = runtime.connect(pDivOpIUnk, pNbSourceIUnk3, "passc.INb");
        long ConnID4 = runtime.connect(pDivOpIUnk, pNbSourceIUnk4, "passc.INb");

        // create connections for A-(A+B)
        long ConnID5 = runtime.connect(pSubOpIUnk, pNbSourceIUnk, "passc.INb");
        long ConnID6 = runtime.connect(pSubOpIUnk, pAddOpIUnk, "passc.INb");

        // create connections for (A-(A+B)) * ( C/D)
        long ConnID7 = runtime.connect(pMulOpIUnk, pSubOpIUnk, "passc.INb");
        long ConnID8 = runtime.connect(pMulOpIUnk, pDivOpIUnk, "passc.INb");


        // create connections with monitoring component
        long ConnID9 = runtime.connect(pMonitorIUnk, pNbSourceIUnk, "passc.INb");
        long ConnID10 = runtime.connect(pMonitorIUnk, pNbSourceIUnk2, "passc.INb");
        long ConnID11 = runtime.connect(pMonitorIUnk, pNbSourceIUnk3, "passc.INb");
        long ConnID12 = runtime.connect(pMonitorIUnk, pNbSourceIUnk4, "passc.INb");
        long ConnID13 = runtime.connect(pMonitorIUnk, pAddOpIUnk, "passc.INb");
        long ConnID14 = runtime.connect(pMonitorIUnk, pSubOpIUnk, "passc.INb");
        long ConnID15 = runtime.connect(pMonitorIUnk, pMulOpIUnk, "passc.INb");
        long ConnID16 = runtime.connect(pMonitorIUnk, pDivOpIUnk, "passc.INb");



        // Get the Nb Interface - the root of the expression tree

        INb pINb = (INb) pMulOpIUnk.QueryInterface("passc.INb");



        // Get the debug interface and dump component configuration to console output
        IDebug pIDebug = (IDebug) runtime.QueryInterface("OpenCOM.IDebug");
        pIDebug.dump();
        pIDebug.visualise();

        // Get the Monitor Interface
        IMonitor pIMonitor = (IMonitor) pMonitorIUnk.QueryInterface("passc.IMonitor");

        int iter;
        for (iter = 0; iter < 4; iter++) {
            System.out.println("eval = " + pINb.eval());

            //pIMonitor.monitor(); TO DO in monitor: implement reconfiguration policy !

            //pIDebug.visualise();

            pINb.reset();
        }
    }
}
