#ifndef __X10_LANG_FINISHSTATE__FINISHSKELETON_H
#define __X10_LANG_FINISHSTATE__FINISHSKELETON_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE_H_NODEPS
#include <x10/lang/FinishState.h>
#undef X10_LANG_FINISHSTATE_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState__RootFinishSkeleton;
} } 
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class SimpleLatch;
} } 
namespace x10 { namespace lang { 

class FinishState__FinishSkeleton : public x10::lang::FinishState   {
    public:
    RTT_H_DECLS_CLASS
    
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > FMGL(ref);
    
    void _instance_init();
    
    x10aux::ref<x10::lang::FinishState> FMGL(me);
    
    void _constructor(x10aux::ref<x10::lang::FinishState__RootFinishSkeleton> root);
    
    void _constructor(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref);
    
    virtual x10aux::ref<x10::io::SerialData> serialize();
    virtual void notifySubActivitySpawn(x10::lang::Place place);
    virtual void notifyActivityCreation();
    virtual void notifyActivityTermination();
    virtual void pushException(x10aux::ref<x10::lang::Throwable> t);
    virtual void waitForFinish();
    virtual x10aux::ref<x10::lang::SimpleLatch> simpleLatch();
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref();
    virtual x10aux::ref<x10::lang::FinishState__FinishSkeleton> x10__lang__FinishState__FinishSkeleton____x10__lang__FinishState__FinishSkeleton__this(
      );
    void __fieldInitializers1928();
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE__FINISHSKELETON_H

namespace x10 { namespace lang { 
class FinishState__FinishSkeleton;
} } 

#ifndef X10_LANG_FINISHSTATE__FINISHSKELETON_H_NODEPS
#define X10_LANG_FINISHSTATE__FINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#include <x10/io/SerialData.h>
#include <x10/lang/Place.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/SimpleLatch.h>
#ifndef X10_LANG_FINISHSTATE__FINISHSKELETON_H_GENERICS
#define X10_LANG_FINISHSTATE__FINISHSKELETON_H_GENERICS
#endif // X10_LANG_FINISHSTATE__FINISHSKELETON_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__FINISHSKELETON_H_NODEPS
